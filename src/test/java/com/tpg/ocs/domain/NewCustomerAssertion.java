package com.tpg.ocs.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class NewCustomerAssertion extends AbstractAssert<NewCustomerAssertion, NewCustomer> {

    public static NewCustomerAssertion assertThat(NewCustomer customer) {

        return new NewCustomerAssertion(customer);
    }

    public NewCustomerAssertion(NewCustomer value) {

        super(value, NewCustomerAssertion.class);
    }

    public NewCustomerAssertion hasFirstName(String value) {

        Assertions.assertThat(actual.getFirstName()).isEqualTo(value);

        return this;
    }

    public NewCustomerAssertion hasSurname(String value) {

        Assertions.assertThat(actual.getSurname()).isEqualTo(value);

        return this;
    }

    public NewCustomerAssertion hasOcsUser(OcsUser value) {

        Assertions.assertThat(actual.getOcsUser()).isEqualTo(value);

        return this;
    }

    public NewCustomerAssertion hasAccountNumber(String value) {

        Assertions.assertThat(actual.getAccountNumber()).isEqualTo(value);

        return this;
    }
}
