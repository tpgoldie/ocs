package com.tpg.ocs.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public interface StringValidation {

    default void rejectEmptyOrWhitespace(String fieldName, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, "error.user", "user is required");

        errors.rejectValue(fieldName, String.format("error.user", fieldName));
    }
}
