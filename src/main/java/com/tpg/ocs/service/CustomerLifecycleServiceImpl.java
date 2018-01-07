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
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CustomerLifecycleServiceImpl implements CustomerLifecycleService, ToCustomerEntity {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLifecycleServiceImpl.class);

    private final UserAuthenticationServiceClient userAuthenticationServiceClient;
    private CustomerLifecycleRepository customerLifecycleRepository;
    private AccountNumberGeneration accountNumberGeneration;
    private final Validator validator;

    @Autowired
    public CustomerLifecycleServiceImpl(UserAuthenticationServiceClient userAuthenticationServiceClient,
                                        CustomerLifecycleRepository customerLifecycleRepository,
                                        AccountNumberGeneration accountNumberGeneration,
                                        Validator validator) {

        this.userAuthenticationServiceClient = userAuthenticationServiceClient;
        this.customerLifecycleRepository = customerLifecycleRepository;
        this.accountNumberGeneration = accountNumberGeneration;
        this.validator = validator;
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_NEW_CUSTOMER')")
    public Optional<Outcome> save(NewCustomer customer) {
        Errors errors = new MapBindingResult(new HashMap<>(), "newCustomer");

        validator.validate(customer, errors);

        if (errors.getErrorCount() > 0) {

            LOGGER.error("Customer validation failed {}", errors.getAllErrors());

            return Optional.empty();
        }

        Optional<OcsUser> user = userAuthenticationServiceClient.createUser(customer.getOcsUser().getUsername(),
                customer.getOcsUser().getPassword());

        String newAccountNo = accountNumberGeneration.generateAccountNumber();

        return user.flatMap(u -> save(customer, u, newAccountNo));
    }

    private Optional<Outcome> save(NewCustomer newCustomer, OcsUser user, String newAccountNo) {

        newCustomer.setOcsUser(user);

        newCustomer.setAccountNumber(newAccountNo);

        LOGGER.info("Generated new account number ...");

        CustomerEntity customerEntity = convert(newCustomer);

        customerLifecycleRepository.save(customerEntity);

        return Optional.of(new NewAccountNumber(newAccountNo));
    }
}
