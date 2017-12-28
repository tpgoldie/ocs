package com.tpg.ocs.context;

import com.tpg.ocs.persistence.repositories.CustomerLifecycleRepository;
import com.tpg.ocs.service.AccountNumberGeneration;
import com.tpg.ocs.service.CustomerLifecycleService;
import com.tpg.ocs.service.CustomerLifecycleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({RepositoriesConfig.class})
public class ServicesConfig {

    @Autowired
    private CustomerLifecycleRepository customerLifecycleRepository;

    @Autowired
    private AccountNumberGeneration accountNumberGeneration;

    @Bean
    public CustomerLifecycleService customerLifecycleService() {
        return new CustomerLifecycleServiceImpl(customerLifecycleRepository, accountNumberGeneration);
    }
}