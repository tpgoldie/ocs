package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CustomerMissingDateOfBirthTest extends InvalidCustomerEntityTest {

    @Test
    public void validateMissingDateOfBirth() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", null, generateId(),
                persistentUser.getUsername(), persistentUser.getPassword());

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);
        assertEquals(1, actual.size());

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasDateOfBirthPath()
                .hasMayNotBeNullMessage()
                .hasMayNotBeNullMessageTemplate();
    }
}
