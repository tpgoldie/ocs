package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.util.DateGeneration;
import com.tpg.ocs.util.UniqueIdGeneration;
import lombok.Getter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Getter
public abstract class CustomerRepositoryTest implements DateGeneration, UniqueIdGeneration {

    @Autowired
    private TestEntityManager entityManager;
}
