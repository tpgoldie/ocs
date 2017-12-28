package com.tpg.ocs.persistence.entities;

import java.time.LocalDate;

public interface CustomerEntityFixture {
    default CustomerEntity newCustomer(String firstName, String surname, LocalDate dateOfBirth, String accountNumber) {

        CustomerEntity entity = new CustomerEntity();

        PersistentName name = new PersistentName();

        name.setFirstName(firstName);
        name.setSurname(surname);

        entity.setName(name);
        entity.setDateOfBirth(dateOfBirth);
        entity.setAccountNumber(accountNumber);

        return entity;
    }
}
