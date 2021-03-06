package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.CustomerEntityFixture;
import com.tpg.ocs.persistence.entities.PersistentUser;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class SaveNewCustomerTest extends CustomerLifecycleRepositoryTest {
    private static Condition<Long> isPositive() {

        return new Condition<Long>() {

            @Override
            public boolean matches(Long value) {

                return value > 0;
            }
        };
    }

    @Test
    public void saveCustomer_newCustomer_newCustomerShouldBeSaved() {

        CustomerEntity customerEntity = newCustomer("John", "Doe",
                generateDate(10, 5, 1987), generateId(), "user-123", "123");

        Optional<Long> actual = customerLifecycleRepository.save(customerEntity);

        getEntityManager().flush();

        assertTrue(actual.isPresent());

        assertThat(actual.get()).is(isPositive());
    }
}
