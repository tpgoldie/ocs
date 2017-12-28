package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class CustomerLifecycleRepositoryImpl implements CustomerLifecycleRepository {

    @PersistenceContext(name = "ocs")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Long> save(CustomerEntity customerEntity) {

        entityManager.persist(customerEntity);

        return Optional.of(customerEntity.getId());
    }
}
