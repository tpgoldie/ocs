package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntityFixture;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class CustomerLifecycleRepositoryTest extends CustomerRepositoryTest implements CustomerEntityFixture {

    @Autowired
    protected CustomerLifecycleRepository customerLifecycleRepository;
}
