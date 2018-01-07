package com.tpg.ocs.domain;

import org.assertj.core.api.AbstractAssert;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.verify;

public class NewCustomerErrorsAssertion extends AbstractAssert<NewCustomerErrorsAssertion, Errors> {

    public static NewCustomerErrorsAssertion assertThat(Errors errors) {

        return new NewCustomerErrorsAssertion(errors);
    }

    public NewCustomerErrorsAssertion(Errors errors) {

        super(errors, NewCustomerErrorsAssertion.class);
    }

    public NewCustomerErrorsAssertion rejectValueForUsername() {

        rejectValue("username");

        return this;
    }

    public NewCustomerErrorsAssertion rejectValueForPassword() {

        rejectValue("password");

        return this;
    }

    private void rejectValue(String fieldName) {

        verify(actual).getFieldValue(fieldName);

        String errorKey = "error.user";

        verify(actual).rejectValue(fieldName, errorKey, null, "user is required");

        verify(actual).rejectValue(fieldName, errorKey);
    }
}
