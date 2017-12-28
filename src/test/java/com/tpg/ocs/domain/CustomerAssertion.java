package com.tpg.ocs.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CustomerAssertion extends AbstractAssert<CustomerAssertion, Customer> {

    public static CustomerAssertion assertThat(Customer customer) {

        return new CustomerAssertion(customer);
    }

    public CustomerAssertion(Customer value) {

        super(value, CustomerAssertion.class);
    }

    public CustomerAssertion hasFirstName(String value) {

        Assertions.assertThat(actual.getFirstName()).isEqualTo(value);

        return this;
    }

    public CustomerAssertion hasSurname(String value) {

        Assertions.assertThat(actual.getSurname()).isEqualTo(value);

        return this;
    }

    public CustomerAssertion hasAccountNumber(String value) {

        Assertions.assertThat(actual.getAccountNumber()).isEqualTo(value);

        return this;
    }
}
