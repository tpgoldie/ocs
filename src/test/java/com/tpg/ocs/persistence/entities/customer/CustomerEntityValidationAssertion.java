package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.assertj.core.api.AbstractAssert;

import javax.validation.ConstraintViolation;

public class CustomerEntityValidationAssertion extends AbstractAssert<CustomerEntityValidationAssertion, ConstraintViolation<CustomerEntity>> {

    public static CustomerEntityValidationAssertion assertThat(ConstraintViolation<CustomerEntity> violation) {

        return new CustomerEntityValidationAssertion(violation);
    }

    CustomerEntityValidationAssertion(ConstraintViolation<CustomerEntity> violation) {
        super(violation, CustomerEntityValidationAssertion.class);
    }

    public CustomerEntityValidationAssertion hasFirstNamePath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("name.firstName");

        return this;
    }

    public CustomerEntityValidationAssertion hasSurnamePath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("name.surname");

        return this;
    }

    public CustomerEntityValidationAssertion hasAccountNumberPath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("accountNumber");

        return this;
    }

    public CustomerEntityValidationAssertion hasDateOpenedPath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("dateOpened");

        return this;
    }

    public CustomerEntityValidationAssertion hasUsernamePath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("user.username");

        return this;
    }

    public CustomerEntityValidationAssertion hasPasswordPath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("user.password");

        return this;
    }

    public CustomerEntityValidationAssertion hasDateOfBirthPath() {

        org.assertj.core.api.Assertions.assertThat(actual.getPropertyPath().toString()).isEqualTo("dateOfBirth");

        return this;
    }

    public CustomerEntityValidationAssertion hasMayNotBeEmptyMessage() {

        org.assertj.core.api.Assertions.assertThat(actual.getMessage())
                .isEqualTo("may not be empty");

        return this;
    }

    public CustomerEntityValidationAssertion hasMayNotBeEmptyMessageTemplate() {

        org.assertj.core.api.Assertions.assertThat(actual.getMessageTemplate())
                .isEqualTo("{org.hibernate.validator.constraints.NotEmpty.message}");

        return this;
    }

    public CustomerEntityValidationAssertion hasSizeMessage(int lowerLimit, int upperLimit) {

        org.assertj.core.api.Assertions.assertThat(actual.getMessage())
                .isEqualTo(String.format("size must be between %d and %d", lowerLimit, upperLimit));

        return this;
    }

    public CustomerEntityValidationAssertion hasSizeMessageTemplate() {

        org.assertj.core.api.Assertions.assertThat(actual.getMessageTemplate())
                .isEqualTo("{javax.validation.constraints.Size.message}");

        return this;
    }

    public CustomerEntityValidationAssertion hasMayNotBeNullMessage() {

        org.assertj.core.api.Assertions.assertThat(actual.getMessage())
                .isEqualTo("may not be null");

        return this;
    }

    public CustomerEntityValidationAssertion hasMayNotBeNullMessageTemplate() {

        org.assertj.core.api.Assertions.assertThat(actual.getMessageTemplate())
                .isEqualTo("{javax.validation.constraints.NotNull.message}");

        return this;
    }
}
