package com.tpg.ocs.persistence.entities;

import java.time.LocalDate;

public interface CustomerEntityFixture {
    default CustomerEntity newCustomer(String firstName, String surname, LocalDate dateOfBirth, String accountNumber, String username, String password) {

        CustomerEntity entity = new CustomerEntity();

        PersistentName name = new PersistentName();

        name.setFirstName(firstName);
        name.setSurname(surname);

        entity.setName(name);

        PersistentUser user = new PersistentUser();

        user.setUsername(username);
        user.setPassword(password);

        entity.setUser(user);

        entity.setDateOfBirth(dateOfBirth);
        entity.setAccountNumber(accountNumber);

        entity.setDateOpened(LocalDate.now());

        return entity;
    }
}
