package com.tpg.ocs.service;

import com.tpg.ocs.client.UserAuthenticationServiceClient;
import com.tpg.ocs.domain.NewCustomerDomainFixture;
import com.tpg.ocs.domain.NewCustomerValidator;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.util.UniqueIdGeneration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Validator;

@RunWith(MockitoJUnitRunner.class)
public abstract class CustomerLifecycleServiceImplTest implements NewCustomerDomainFixture, UniqueIdGeneration {
    @Mock
    protected UserAuthenticationServiceClient userAuthenticationServiceClient;

    @Mock
    protected CustomerLifecycleRepository customerLifecycleRepository;

    @Mock
    protected AccountNumberGeneration accountNumberGeneration;

    protected Validator validator;

    protected CustomerLifecycleServiceImpl serviceImpl;

    @Before
    public void setUp() {

        validator = new NewCustomerValidator();

        serviceImpl = new CustomerLifecycleServiceImpl(userAuthenticationServiceClient, customerLifecycleRepository,
                accountNumberGeneration, validator);
    }
}
