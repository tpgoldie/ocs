package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.util.DateGeneration;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerWithInvalidPasswordTest extends InvalidCustomerEntityTest implements DateGeneration {

    @Test
    public void validatePasswordTooLarge() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", generateDate(12, 11, 1975),
                generateId(), persistentUser.getUsername(), "12345678901");

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasPasswordPath()
                .hasSizeMessage(3, 10)
                .hasSizeMessageTemplate();
    }

    @Test
    public void validatePasswordTooSmall() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", generateDate(12, 11, 1975),
                generateId(), persistentUser.getUsername(), "12");

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasPasswordPath()
                .hasSizeMessage(3, 10)
                .hasSizeMessageTemplate();
    }
}
