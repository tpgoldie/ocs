package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.persistence.entities.CustomerEntityAssertion;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class CustomerLifecycleRepositoryAssertion {

    private CustomerLifecycleRepository customerLifecycleRepository;

    public static CustomerLifecycleRepositoryAssertion assertThat(CustomerLifecycleRepository customerLifecycleRepository) {
        return new CustomerLifecycleRepositoryAssertion(customerLifecycleRepository);
    }

    public CustomerLifecycleRepositoryAssertion(CustomerLifecycleRepository customerLifecycleRepository) {

        this.customerLifecycleRepository = customerLifecycleRepository;
    }

    public CustomerLifecycleRepositoryAssertion savesNewCustomer(Customer customer) {

        ArgumentCaptor<CustomerEntity> customerEntityArgumentCaptor = ArgumentCaptor.forClass(CustomerEntity.class);

        verify(customerLifecycleRepository).save(customerEntityArgumentCaptor.capture());

        CustomerEntity actual = customerEntityArgumentCaptor.getValue();

        PersistentName name = new PersistentName();

        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        CustomerEntityAssertion.assertThat(actual)
                .hasName(name)
                .hasAccountNumber(customer.getAccountNumber());

        return this;
    }

    public CustomerLifecycleRepositoryAssertion doesNotSaveNewCustomer() {

        verify(customerLifecycleRepository, never()).save(isA(CustomerEntity.class));

        return this;
    }
}
