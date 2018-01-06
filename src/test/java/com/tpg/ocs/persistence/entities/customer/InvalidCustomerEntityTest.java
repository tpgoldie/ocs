package com.tpg.ocs.persistence.entities.customer;

import com.tpg.ocs.persistence.entities.CustomerEntityFixture;
import com.tpg.ocs.persistence.entities.PersistentUser;
import com.tpg.ocs.util.DateGeneration;
import com.tpg.ocs.util.UniqueIdGeneration;
import org.junit.Before;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public abstract class InvalidCustomerEntityTest implements CustomerEntityFixture, UniqueIdGeneration, DateGeneration {

    protected PersistentUser persistentUser;

    protected LocalValidatorFactoryBean validator;

    @Before
    public void setUp() {

        persistentUser = new PersistentUser();

        persistentUser.setUsername("123-user");
        persistentUser.setPassword("123");

        validator = new LocalValidatorFactoryBean();

        validator.afterPropertiesSet();
    }
}
