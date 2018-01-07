package com.tpg.ocs.service;

import com.tpg.ocs.client.UserAuthenticationServiceClientAssertion;
import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.OcsUser;
import com.tpg.ocs.domain.OcsUserDomainFixture;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.service.CustomerLifecycleService.Outcome;
import org.junit.Test;

import java.util.Optional;

import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static com.tpg.ocs.persistence.repositories.CustomerLifecycleRepositoryAssertion.assertThat;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

public class FailToCreateUserDetailsTest extends CustomerLifecycleServiceImplTest {

    @Test
    public void handleUserNotCreated_newCustomer_shouldNotSaveNewCustomer() {

        NewCustomer customer = newCustomerJohnDoe();

        Optional<Outcome> actual = whenSavingNewCustomer(customer);

        assertFalse(actual.isPresent());

        UserAuthenticationServiceClientAssertion.assertThat(userAuthenticationServiceClient)
            .createsUser(customer.getUsername(), customer.getPassword());

        AccountNumberGenerationAssertion.assertThat(accountNumberGeneration).accountNumberGenerated();

        assertThat(customerLifecycleRepository).doesNotSaveNewCustomer();
    }

    private Optional<Outcome> whenSavingNewCustomer(NewCustomer customer) {

        when(accountNumberGeneration.generateAccountNumber()).thenReturn(generateId().toUpperCase());

        when(userAuthenticationServiceClient.createUser(customer.getUsername(), customer.getPassword()))
            .thenReturn(Optional.empty());

        when(customerLifecycleRepository.save(isA(CustomerEntity.class))).thenReturn(Optional.of(100L));

        return serviceImpl.save(customer);
    }
}
