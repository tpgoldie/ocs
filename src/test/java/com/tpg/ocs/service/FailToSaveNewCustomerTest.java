package com.tpg.ocs.service;

import com.tpg.ocs.client.UserAuthenticationServiceClientAssertion;
import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.OcsUser;
import com.tpg.ocs.domain.OcsUserDomainFixture;
import com.tpg.ocs.domain.UserRole;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.service.CustomerLifecycleService.Outcome;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static com.tpg.ocs.persistence.repositories.CustomerLifecycleRepositoryAssertion.assertThat;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FailToSaveNewCustomerTest extends CustomerLifecycleServiceImplTest implements OcsUserDomainFixture {

    @Test
    public void handleInvalidCustomer_newCustomer_shouldNotSaveNewCustomer() {

        NewCustomer customer = newCustomerJohnDoe();

        customer.getOcsUser().setPassword("");

        Optional<Outcome> actual = whenSavingNewCustomer(customer);

        assertFalse(actual.isPresent());

        AccountNumberGenerationAssertion.assertThat(accountNumberGeneration)
            .accountNumberNotGenerated();

        assertThat(customerLifecycleRepository).doesNotSaveNewCustomer();

        UserAuthenticationServiceClientAssertion.assertThat(userAuthenticationServiceClient)
            .doesNotCreateUser(customer.getUsername(), customer.getPassword());
    }

    private Optional<Outcome> whenSavingNewCustomer(NewCustomer customer) {

        OcsUser ocsUser = anOcsUser(customer.getUsername(), customer.getPassword(), singletonList(STANDARD_CUSTOMER_ROLE));

        when(userAuthenticationServiceClient.createUser(customer.getUsername(), customer.getPassword()))
            .thenReturn(Optional.of(ocsUser));

        when(customerLifecycleRepository.save(isA(CustomerEntity.class))).thenReturn(Optional.of(100L));

        return serviceImpl.save(customer);
    }
}
