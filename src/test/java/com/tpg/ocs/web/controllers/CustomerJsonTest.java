package com.tpg.ocs.web.controllers;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.domain.CustomerAssertion;
import com.tpg.ocs.domain.CustomerDomainFixture;
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
public class CustomerJsonTest implements CustomerDomainFixture {

    @Autowired
    private JacksonTester<Customer> jacksonTester;

    @Test
    public void serialize() throws IOException {

        Customer customer = johnDoe();

        JsonContent<Customer> actual = jacksonTester.write(customer);

        assertThat(actual)
            .hasJsonPathStringValue("@.firstName");

        assertThat(actual)
            .hasJsonPathStringValue("@.surname");

        assertThat(actual)
            .hasJsonPathStringValue("@.accountNumber");
    }

    @Test
    public void deserialize() throws IOException {

        Customer expected = newCustomer("John", "Doe", "123");

        Customer actual = jacksonTester.read(new ClassPathResource("customer.json")).getObject();

        CustomerAssertion.assertThat(actual)
            .hasFirstName(expected.getFirstName())
            .hasSurname(expected.getSurname())
            .hasAccountNumber(expected.getAccountNumber());
    }
}
