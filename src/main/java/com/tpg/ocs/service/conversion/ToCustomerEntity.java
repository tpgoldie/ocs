package com.tpg.ocs.service.conversion;

import com.tpg.ocs.domain.Customer;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;

public interface ToCustomerEntity extends ToEntity<Customer, CustomerEntity> {
    default CustomerEntity convert(Customer source) {

        CustomerEntity entity = new CustomerEntity();

        PersistentName name = setName(source);

        entity.setName(name);
        entity.setAccountNumber(source.getAccountNumber());

        return entity;
    }

    default PersistentName setName(Customer customer) {

        PersistentName name = new PersistentName();

        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        return name;
    }
}
