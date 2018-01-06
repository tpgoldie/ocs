package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CustomerMissingDateOpenedTest extends InvalidCustomerEntityTest {

    @Test
    public void validateMissingDateOpened() {

        CustomerEntity customerEntity = newCustomer("John", "Doe", generateDate(12, 3, 1990),
                generateId(), persistentUser.getUsername(), persistentUser.getPassword());

        customerEntity.setDateOpened(null);

        Set<ConstraintViolation<CustomerEntity>> actual = validator.validate(customerEntity);
        assertEquals(1, actual.size());

        ConstraintViolation<CustomerEntity> violation = actual.iterator().next();

        CustomerEntityValidationAssertion.assertThat(violation)
                .hasDateOpenedPath()
                .hasMayNotBeNullMessage()
                .hasMayNotBeNullMessageTemplate();
    }
}
