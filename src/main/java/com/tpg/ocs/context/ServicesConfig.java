package com.tpg.ocs.context;

import com.tpg.ocs.client.UserAuthenticationServiceClient;
import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.AccountNumberGeneration;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.service.CustomerLifecycleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.Validator;

@Configuration
@Import({RepositoriesConfig.class})
public class ServicesConfig implements ObjectMapping {

    @Autowired
    private CustomerLifecycleRepository customerLifecycleRepository;

    @Autowired
    private UserAuthenticationServiceClient userAuthenticationServiceClient;

    @Autowired
    private AccountNumberGeneration accountNumberGeneration;

    @Autowired
    private Validator validator;

    @Bean
    public CustomerLifecycleService customerLifecycleService() {

        return new CustomerLifecycleServiceImpl(userAuthenticationServiceClient, customerLifecycleRepository, accountNumberGeneration, validator);
    }
}
