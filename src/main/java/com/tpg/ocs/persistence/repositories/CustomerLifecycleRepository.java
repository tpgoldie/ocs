package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.springframework.data.repository.Repository;

import javax.validation.Valid;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CustomerLifecycleRepository extends Repository<CustomerEntity, Long> {

    Optional<Long> save(CustomerEntity customerEntity);
}
