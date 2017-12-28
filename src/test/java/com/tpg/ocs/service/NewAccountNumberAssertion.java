package com.tpg.ocs.service;

import com.tpg.ocs.service.CustomerLifecycleService.NewAccountNumber;
import org.assertj.core.api.AbstractAssert;

public class NewAccountNumberAssertion extends AbstractAssert<NewAccountNumberAssertion, NewAccountNumber> {

    public static NewAccountNumberAssertion assertThat(NewAccountNumber newAccountNumber) {

        return new NewAccountNumberAssertion(newAccountNumber);
    }

    public NewAccountNumberAssertion(NewAccountNumber actual) {

        super(actual, NewAccountNumberAssertion.class);
    }

    public NewAccountNumberAssertion hasAccountNumber(String value) {

        isNotNull();

        if (!actual.getAccountNumber().equals(value)) {
            failWithMessage("Expected new account number to be <%s> but was <%s>",
                value, actual.getAccountNumber());
        }

        return this;
    }
}