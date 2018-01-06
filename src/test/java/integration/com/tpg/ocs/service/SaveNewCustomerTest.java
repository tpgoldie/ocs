package integration.com.tpg.ocs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tpg.ocs.OcsWebApplication;
import com.tpg.ocs.client.UserAuthentication;
import com.tpg.ocs.client.UsernameAndPassword;
import com.tpg.ocs.context.RepositoriesConfig;
import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.NewCustomerDomainFixture;
import com.tpg.ocs.domain.OcsUser;
import com.tpg.ocs.domain.OcsUserDomainFixture;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.web.controllers.UserAuthenticationFixture;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OcsWebApplication.class, RepositoriesConfig.class}, webEnvironment = RANDOM_PORT)
public class SaveNewCustomerTest implements NewCustomerDomainFixture, UserAuthenticationFixture, OcsUserDomainFixture {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerLifecycleService customerLifecycleService;

    private UserAuthentication userAuthentication;

    @Rule
    public WireMockRule userAuthenticationService = new WireMockRule(options().port(8181));

    @Before
    public void setUp() {

        userAuthentication = ocsAnonymousUserNewCustomerUserAuthentication();
    }

    @Test
    public void saveNewCustomer_newCustomer_shouldSaveNewCustomer() throws JsonProcessingException {

        String id = generateId();

        NewCustomer customer = newCustomer(format("%s-first-name", id), format("%s-surname", id),
                "", id, generateId(), generateDate(14, 7, 1976));

        stubUserAuthenticationCheck();

        stubNewUserCreation(customer);

        customerLifecycleService.save(customer);
    }

    private String stubUserAuthenticationCheck() throws JsonProcessingException {
        String userAuthenticationJson = objectMapper.writeValueAsString(userAuthentication);

        String userAuthenticationUrl = format("/ocs/users/%s", userAuthentication.getUsername());

        stubFor(WireMock.post(userAuthenticationUrl)
                .willReturn(okJson(userAuthenticationJson)));

        return userAuthenticationUrl;
    }

    private String stubNewUserCreation(NewCustomer customer) throws JsonProcessingException {

        String newUserUrl = "/ocs/users";

        UsernameAndPassword userDetails = new UsernameAndPassword(customer.getUsername(), customer.getPassword());

        String usernameAndPasswordJson = objectMapper.writeValueAsString(userDetails);

        OcsUser ocsUser = anOcsUser(customer.getOcsUser().getUsername(),
                customer.getOcsUser().getPassword(), singletonList(STANDARD_CUSTOMER_ROLE));

        String newUserJson = objectMapper.writeValueAsString(ocsUser);

        stubFor(WireMock.post(newUserUrl)
                .withRequestBody(equalTo(usernameAndPasswordJson))
                .willReturn(okJson(newUserJson)));

        return newUserUrl;
    }

}
