package com.tpg.ocs.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpg.ocs.client.UserAuthentication;
import com.tpg.ocs.context.*;
import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.AccountNumberGeneration;
import com.tpg.ocs.service.DefaultAccountNumberGenerator;
import com.tpg.ocs.util.UniqueIdGeneration;
import com.tpg.ocs.domain.CustomerDomainFixture;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.service.CustomerLifecycleService.NewAccountNumber;
import com.tpg.ocs.web.security.BasicAuthenticationTokens;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {CustomerLifecycleController.class})
@ActiveProfiles(profiles = {"dev"})
@ContextConfiguration(classes = {PersistenceConfig.class, RepositoriesConfig.class, ServicesConfig.class, ClientsConfig.class, OcsSecurityConfig.class})
public class CustomerLifecycleControllerTest implements CustomerDomainFixture, UniqueIdGeneration,
        UserAuthenticationFixture, BasicAuthenticationTokens {

    @TestConfiguration
    static class TestConfig {

        @Bean
        public AccountNumberGeneration accountNumberGeneration() {
            return new DefaultAccountNumberGenerator();
        }

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            RestTemplateBuilder builder = new RestTemplateBuilder();

            builder.rootUri("http://localhoat:8080");

            return builder;
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerLifecycleService customerLifecycleService;

    @MockBean
    private CustomerLifecycleRepository customerLifecycleRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private DataSource dataSource;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private UserAuthentication userAuthentication = johnDoeUserAuthentication();

    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {

        userAuthentication = johnDoeUserAuthentication();

        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    public void saveNewCustomer_newCustomer_shouldSaveNewCustomer() throws Exception {

        Customer customer = newCustomer("John", "Doe", generateId());

        String json = objectMapper.writeValueAsString(customer);

        NewAccountNumber outcome = new NewAccountNumber(generateId());

        when(customerLifecycleService.save(customer)).thenReturn(Optional.of(outcome));

        mockMvc.perform(post("/shop/customers")
            .content(json)
                .param("username", userAuthentication.getUsername())
                .param("password", userAuthentication.getPassword())
            .contentType(APPLICATION_JSON_UTF8)
            .header(AUTHORIZATION, createBasicAuthentication()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").exists());

        verify(customerLifecycleService).save(customer);
    }
}






