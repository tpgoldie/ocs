package com.tpg.ocs.service;

import com.tpg.ocs.client.UserAuthenticationServiceClient;
import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.OcsUser;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.conversion.ToCustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerLifecycleServiceImpl implements CustomerLifecycleService, ToCustomerEntity {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLifecycleServiceImpl.class);

    private final UserAuthenticationServiceClient userAuthenticationServiceClient;
    private CustomerLifecycleRepository customerLifecycleRepository;
    private AccountNumberGeneration accountNumberGeneration;

    @Autowired
    public CustomerLifecycleServiceImpl(UserAuthenticationServiceClient userAuthenticationServiceClient,
                                        CustomerLifecycleRepository customerLifecycleRepository,
                                        AccountNumberGeneration accountNumberGeneration) {

        this.userAuthenticationServiceClient = userAuthenticationServiceClient;
        this.customerLifecycleRepository = customerLifecycleRepository;
        this.accountNumberGeneration = accountNumberGeneration;
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_NEW_CUSTOMER')")
    public Optional<Outcome> save(NewCustomer customer) {
        Optional<OcsUser> user = userAuthenticationServiceClient.createUser(customer.getOcsUser().getUsername(),
                customer.getOcsUser().getPassword());

        String newAccountNo = accountNumberGeneration.generateAccountNumber();

        customer.setOcsUser(user.get());

        customer.setAccountNumber(newAccountNo);

        LOGGER.info("Generated new account number ...");

        CustomerEntity customerEntity = convert(customer);

        customerLifecycleRepository.save(customerEntity);

        return Optional.of(new NewAccountNumber(newAccountNo));
    }
}
