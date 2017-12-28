package com.tpg.ocs.service;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.service.conversion.ToCustomerEntity;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerLifecycleServiceImpl implements CustomerLifecycleService, ToCustomerEntity {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerLifecycleServiceImpl.class);

    private CustomerLifecycleRepository customerLifecycleRepository;
    private AccountNumberGeneration accountNumberGeneration;

    @Autowired
    public CustomerLifecycleServiceImpl(CustomerLifecycleRepository customerLifecycleRepository,
                                        AccountNumberGeneration accountNumberGeneration) {

        this.customerLifecycleRepository = customerLifecycleRepository;
        this.accountNumberGeneration = accountNumberGeneration;
    }

    @Override
    public Optional<Outcome> save(Customer customer) {
        String newAccountNo = accountNumberGeneration.generateAccountNumber();

        customer.setAccountNumber(newAccountNo);

        LOGGER.info("Generated new account number ...");

        CustomerEntity customerEntity = convert(customer);

        customerLifecycleRepository.save(customerEntity);

        return Optional.of(new NewAccountNumber(newAccountNo));
    }
}
