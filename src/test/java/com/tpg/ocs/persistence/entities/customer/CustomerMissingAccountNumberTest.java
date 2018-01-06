package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentUser;
import com.tpg.ocs.util.DateGeneration;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMissingAccountNumberTest extends InvalidCustomerEntityTest implements DateGeneration {

    @Test
    public void validateMissingFirstName() {

        CustomerEntity customerEntity = newCustomer("John", "Doe",
                generateDate(12, 11, 1975), "", persistentUser.getUsername(), persistentUser.getPassword());

        customerEntity.setDateOpened(LocalDate.now());

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);

        assertThat(actual.size()).isEqualTo(1);

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasAccountNumberPath()
                .hasMayNotBeEmptyMessage()
                .hasMayNotBeEmptyMessageTemplate();
    }
}
