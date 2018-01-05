package com.tpg.ocs.service;

import com.tpg.ocs.client.UserAuthenticationServiceClient;
import com.tpg.ocs.util.UniqueIdGeneration;
import com.tpg.ocs.domain.NewCustomerDomainFixture;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class CustomerLifecycleServiceImplTest implements NewCustomerDomainFixture, UniqueIdGeneration {
    @Mock
    protected UserAuthenticationServiceClient userAuthenticationServiceClient;

    @Mock
    protected CustomerLifecycleRepository customerLifecycleRepository;

    @Mock
    protected AccountNumberGeneration accountNumberGeneration;

    @InjectMocks
    protected CustomerLifecycleServiceImpl serviceImpl;
}
