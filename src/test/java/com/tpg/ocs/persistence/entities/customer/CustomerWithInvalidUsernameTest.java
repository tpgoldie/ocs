package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentUser;
import com.tpg.ocs.util.DateGeneration;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerWithInvalidUsernameTest extends InvalidCustomerEntityTest implements DateGeneration {

    @Test
    public void validateUsernameTooLarge() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", generateDate(12, 11, 1975),
                generateId(), "12345678901", persistentUser.getPassword());

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasUsernamePath()
                .hasSizeMessage(3, 10)
                .hasSizeMessageTemplate();
    }

    @Test
    public void validateUsernameTooSmall() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", generateDate(12, 11, 1975),
                generateId(), "12", persistentUser.getPassword());

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasUsernamePath()
                .hasSizeMessage(3, 10)
                .hasSizeMessageTemplate();
    }
}
