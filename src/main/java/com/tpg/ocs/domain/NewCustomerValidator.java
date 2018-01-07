package com.tpg.ocs.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCustomerValidator implements Validator, StringValidation {
    @Override
    public boolean supports(Class<?> aClass) {

        return NewCustomer.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        NewCustomer newCustomer = (NewCustomer) object;

        checkUsernameField(newCustomer, errors);

        checkPasswordField(newCustomer, errors);
    }

    private void checkUsernameField(NewCustomer newCustomer, Errors errors) {

        String fieldName = "username";

        if (newCustomer.getOcsUser() != null && StringUtils.isEmpty(newCustomer.getOcsUser().getUsername())) {

            rejectEmptyOrWhitespace(fieldName, errors);
        }
    }

    private void checkPasswordField(NewCustomer newCustomer, Errors errors) {

        String fieldName = "password";

        if (newCustomer.getOcsUser() != null && StringUtils.isEmpty(newCustomer.getOcsUser().getPassword())) {

            rejectEmptyOrWhitespace(fieldName, errors);
        }
    }
}
