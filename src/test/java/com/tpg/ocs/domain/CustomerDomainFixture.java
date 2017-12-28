package com.tpg.ocs.domain;

import com.tpg.ocs.util.UniqueIdGeneration;

public interface CustomerDomainFixture extends UniqueIdGeneration {
    default Customer johnDoe() {
        return newCustomer("John", "Doe", generateId());
    }

    default Customer newCustomer(String firstName, String surname, String accountNumber) {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setSurname(surname);
        customer.setAccountNumber(accountNumber);

        return customer;
    }
}
