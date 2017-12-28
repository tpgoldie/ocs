package com.tpg.ocs.persistence.repositories;

import com.tpg.ocs.persistence.entities.CustomerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CustomerQueryRepository extends org.springframework.data.repository.Repository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByAccountNumber(String accountName);
}
