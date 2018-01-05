package com.tpg.ocs.web.controllers;

import com.tpg.ocs.domain.NewCustomerAssertion;
import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.NewCustomerDomainFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class NewCustomerJsonTest implements NewCustomerDomainFixture {

    @Autowired
    private JacksonTester<NewCustomer> jacksonTester;

    @Test
    public void serialize() throws IOException {

        NewCustomer customer = newCustomerJohnDoe();

        JsonContent<NewCustomer> actual = jacksonTester.write(customer);

        assertThat(actual)
            .hasJsonPathStringValue("@.firstName");

        assertThat(actual)
            .hasJsonPathStringValue("@.surname");

        assertThat(actual)
            .hasJsonPathStringValue("@.accountNumber");
    }

    @Test
    public void deserialize() throws IOException {

        NewCustomer expected = newCustomerJohnDoe();
        expected.setAccountNumber("ABC123");

        NewCustomer actual = jacksonTester.read(new ClassPathResource("newCustomer.json")).getObject();

        NewCustomerAssertion.assertThat(actual)
            .hasFirstName(expected.getFirstName())
            .hasSurname(expected.getSurname())
            .hasOcsUser(expected.getOcsUser())
            .hasAccountNumber(expected.getAccountNumber());
    }
}
