package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.util.DateGeneration;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMissingSurnameTest extends InvalidCustomerEntityTest implements DateGeneration {

    @Test
    public void validateMissingFirstName() {

        CustomerEntity customerEntity = newCustomer("John", "", generateDate(12, 11, 1975),
                generateId(), persistentUser.getUsername(), persistentUser.getPassword());

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasSurnamePath()
                .hasMayNotBeEmptyMessage()
                .hasMayNotBeEmptyMessageTemplate();
    }
}
