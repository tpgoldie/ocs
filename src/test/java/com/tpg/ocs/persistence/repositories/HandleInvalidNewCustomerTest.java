package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.assertj.core.api.Condition;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandleInvalidNewCustomerTest extends CustomerLifecycleRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void handleInvalidNewCustomer_invalidNewCustomer_invalidNewCustomerShouldNotBeSaved() {

        expectedException.expect(ConstraintViolationException.class);

        expectedException.expectMessage(containsString("Validation failed"));

        expectedException.expectMessage(containsString("propertyPath=name.surname"));

        expectedException.expectMessage(containsString("interpolatedMessage='may not be empty'"));

        CustomerEntity customerEntity = newCustomer("John", "", generateDate(10, 5, 1987),
                generateId(), "jdoe", "ABC-123");

        Optional<Long> actual = customerLifecycleRepository.save(customerEntity);

        getEntityManager().flush();

        assertFalse(actual.isPresent());
    }
}
