package com.tpg.ocs.service;

import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.repositories.AccountNumberGenerationAssertion;
import com.tpg.ocs.service.CustomerLifecycleService.NewAccountNumber;
import com.tpg.ocs.service.CustomerLifecycleService.Outcome;
import org.junit.Test;

import java.util.Optional;

import static com.tpg.ocs.persistence.repositories.CustomerLifecycleRepositoryAssertion.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class SaveNewCustomerTest extends CustomerLifecycleServiceImplTest {

    @Test
    public void saveCustomer_newCustomer_shouldSaveNewCustomer() {

        NewCustomer customer = newCustomerJohnDoe();

        Optional<Outcome> actual = whenSavingNewCustomer(customer);

        assertTrue(actual.isPresent());

        NewAccountNumberAssertion.assertThat((NewAccountNumber) actual.get())
                .hasAccountNumber(customer.getAccountNumber());

        AccountNumberGenerationAssertion.assertThat(accountNumberGeneration).accountNumberGenerated();

        assertThat(customerLifecycleRepository).savesNewCustomer(customer);
    }

    private Optional<Outcome> whenSavingNewCustomer(NewCustomer customer) {

        when(accountNumberGeneration.generateAccountNumber()).thenReturn(customer.getAccountNumber());

        when(userAuthenticationServiceClient.createUser(customer.getUsername(), customer.getPassword())).thenReturn(Optional.of(customer.getOcsUser()));

        when(customerLifecycleRepository
            .save(isA(CustomerEntity.class))).thenReturn(Optional.of(100L));

        return serviceImpl.save(customer);
    }
}
