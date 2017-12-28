package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.GivenCustomerEntity;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.tpg.ocs.persistence.entities.CustomerEntityAssertion.assertThat;
import static org.junit.Assert.assertTrue;

public class CustomerQueryRepositoryTest extends CustomerRepositoryTest implements GivenCustomerEntity {

    @Autowired
    private CustomerQueryRepository customerQueryRepository;

    @Test
    public void findByAccountNumber_customer_shouldFindCustomerByAccountName() {
        CustomerEntity customer = givenCustomer();

        Optional<CustomerEntity> actual = whenFindingCustomerByAccountName(customer);

        assertTrue(actual.isPresent());

        assertThat(actual.get())
                .hasName(customer.getName())
                .hasAccountNumber(customer.getAccountNumber());
    }

    private Optional<CustomerEntity> whenFindingCustomerByAccountName(CustomerEntity customer) {

        return customerQueryRepository.findByAccountNumber(customer.getAccountNumber());
    }
}
