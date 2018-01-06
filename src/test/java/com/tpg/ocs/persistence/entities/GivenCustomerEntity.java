package com.tpg.ocs.persistence.entities;

import com.tpg.ocs.util.DateGeneration;
import com.tpg.ocs.util.UniqueIdGeneration;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public interface GivenCustomerEntity extends UniqueIdGeneration, DateGeneration, CustomerEntityFixture {

    default CustomerEntity givenCustomer() {

        CustomerEntity customer = newCustomer("John", "Doe", generateDate(12, 3, 1975),
                generateId(), "jdoe", "ABC-123");

        TestEntityManager entityManager = getEntityManager();

        return entityManager.persist(customer);
    }

    TestEntityManager getEntityManager();
}
