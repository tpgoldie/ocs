package com.tpg.ocs.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tpg.ocs.client.UserAuthentication;
import com.tpg.ocs.client.UsernameAndPassword;
import com.tpg.ocs.context.ClientsConfig;
import com.tpg.ocs.context.WebConfig;
import com.tpg.ocs.domain.*;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;
import com.tpg.ocs.persistence.entities.PersistentUser;
import com.tpg.ocs.persistence.entities.PersistentUserFixture;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.AccountNumberGeneration;
import com.tpg.ocs.util.UniqueIdGeneration;
import com.tpg.ocs.web.security.BasicAuthenticationTokens;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static com.tpg.ocs.persistence.entities.CustomerEntityAssertion.assertThat;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.tpg.ocs.service", "com.tpg.ocs.web"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {CustomerLifecycleControllerTest.TestConfig.class, WebConfig.class, ClientsConfig.class})
@AutoConfigureMockMvc
@AutoConfigureJson
@ActiveProfiles(profiles = {"dev"})
public class CustomerLifecycleControllerTest implements NewCustomerDomainFixture, UniqueIdGeneration,
        UserAuthenticationFixture, OcsUserDomainFixture, PersistentUserFixture, BasicAuthenticationTokens {

    private static final int PORT = 8181;

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            RestTemplateBuilder builder = new RestTemplateBuilder();

            builder.rootUri(String.format("http://localhost:%d/", PORT));

            return builder;
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountNumberGeneration accountNumberGeneration;

    @MockBean
    private CustomerLifecycleRepository customerLifecycleRepository;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Rule
    public WireMockRule userAuthenticationService = new WireMockRule(options().port(PORT));

    private UserAuthentication userAuthentication;

    @Before
    public void setUp() {

        userAuthentication = ocsAnonymousUserNewCustomerUserAuthentication();
    }

    @Test
    public void saveNewCustomer_newCustomer_shouldSaveNewCustomer() throws Exception {

        NewCustomer customer = newCustomerJohnDoe();

        customer.setAccountNumber("");

        UsernameAndPassword usernameAndPassword = new UsernameAndPassword(customer.getOcsAnonymousUsername(), customer.getOcsAnonymousPassword());

        String usernameAndPasswordJson = objectMapper.writeValueAsString(usernameAndPassword);

        String userAuthenticationUrl = stubUserAuthenticationCheck();

        OcsUser ocsUser = anOcsUser(customer.getOcsUser().getUsername(),
                customer.getOcsUser().getPassword(), singletonList(STANDARD_CUSTOMER_ROLE));

        String newUserJson = objectMapper.writeValueAsString(ocsUser);

        String newUserUrl = stubNewUserCreation(customer, newUserJson);

        String accountNo = generateId().toUpperCase();

        when(accountNumberGeneration.generateAccountNumber()).thenReturn(accountNo);

        String newCustomersUrl = String.format("http://localhost:%d/ocs/customers", PORT);

        String newCustomerJson = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post(newCustomersUrl)
            .contentType(APPLICATION_JSON_UTF8)
            .content(newCustomerJson)
            .header(AUTHORIZATION, createBasicAuthentication("ocs_anon_user", "ocs_new_customer")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(jsonPath("$.surname").value(customer.getSurname()))
                .andExpect(jsonPath("$.ocsUser.username").value(customer.getUsername()))
                .andExpect(jsonPath("$.ocsUser.password").value(customer.getPassword()))
                .andExpect(jsonPath("$.accountNumber").value(accountNo));

        WireMock
            .verify(postRequestedFor(urlEqualTo(userAuthenticationUrl))
            .withRequestBody(equalTo(usernameAndPasswordJson)));

        UsernameAndPassword customerUserDetails = new UsernameAndPassword(customer.getUsername(), customer.getPassword());

        String newUserRequestJson = objectMapper.writeValueAsString(customerUserDetails);

        WireMock
            .verify(postRequestedFor(urlEqualTo(newUserUrl))
            .withRequestBody(equalTo(newUserRequestJson)));

        assertNewCustomerEntity(customer, accountNo);
    }

    private void assertNewCustomerEntity(NewCustomer customer, String accountNo) {

        ArgumentCaptor<CustomerEntity> argumentCaptor = ArgumentCaptor.forClass(CustomerEntity.class);

        verify(customerLifecycleRepository).save(argumentCaptor.capture());

        CustomerEntity actual = argumentCaptor.getValue();

        PersistentName name = new PersistentName();
        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        PersistentUser persistentUser = aPersistentUser(customer.getUsername(), customer.getPassword(),
                singletonList(STANDARD_CUSTOMER_ROLE));

        assertThat(actual)
            .hasName(name)
            .hasAccountNumber(accountNo)
            .hasUser(persistentUser);
    }

    private String stubNewUserCreation(NewCustomer customer, String newUserJson) throws JsonProcessingException {

        String newUserUrl = String.format("/ocs/users");

        UsernameAndPassword userDetails = new UsernameAndPassword(customer.getUsername(), customer.getPassword());

        String usernameAndPasswordJson = objectMapper.writeValueAsString(userDetails);

        stubFor(WireMock.post(newUserUrl)
                .withRequestBody(equalTo(usernameAndPasswordJson))
                .willReturn(okJson(newUserJson)));

        return newUserUrl;
    }

    private String stubUserAuthenticationCheck() throws JsonProcessingException {
        String userAuthenticationJson = objectMapper.writeValueAsString(userAuthentication);

        String userAuthenticationUrl = String.format("/ocs/users/%s", userAuthentication.getUsername());

        stubFor(WireMock.post(userAuthenticationUrl)
            .willReturn(okJson(userAuthenticationJson)));

        return userAuthenticationUrl;
    }
}






