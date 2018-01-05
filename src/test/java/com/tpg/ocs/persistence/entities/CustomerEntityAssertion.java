package com.tpg.ocs.persistence.entities;

import org.assertj.core.api.AbstractAssert;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class CustomerEntityAssertion extends AbstractAssert<CustomerEntityAssertion, CustomerEntity> {

    public static CustomerEntityAssertion assertThat(CustomerEntity customer) {

        return new CustomerEntityAssertion(customer);
    }

    public CustomerEntityAssertion(CustomerEntity actual) {
        super(actual, CustomerEntityAssertion.class);
    }

    public CustomerEntityAssertion hasName(PersistentName name) {

        assertTrue("Name does not match", actual.getName().equals(name));

        return this;
    }

    public CustomerEntityAssertion hasUser(PersistentUser value) {

        assertTrue("User does not match", actual.getUser().equals(value));

        return this;
    }

    public CustomerEntityAssertion hasAccountNumber(String value) {

        assertTrue("Account number does not match", actual.getAccountNumber().equals(value));

        return this;
    }

    public CustomerEntityAssertion hasDateOfBirth(LocalDate value) {

        assertTrue("Date of birth does not match", actual.getDateOfBirth().equals(value));

        return this;
    }
}
