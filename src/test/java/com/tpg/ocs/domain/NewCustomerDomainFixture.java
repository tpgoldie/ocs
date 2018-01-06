package com.tpg.ocs.domain;

import com.tpg.ocs.util.DateGeneration;
import com.tpg.ocs.util.UniqueIdGeneration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;

import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static java.util.Collections.singletonList;

public interface NewCustomerDomainFixture extends UniqueIdGeneration, DateGeneration {
    default NewCustomer newCustomerJohnDoe() {
        return newCustomer("John", "Doe", generateId(), "jdoe", "ABC-213", generateDate(12, 5, 1975));
    }

    default NewCustomer newCustomer(String firstName, String surname, String accountNumber, String username, String password, LocalDate dob) {

        NewCustomer customer = new NewCustomer();

        customer.setFirstName(firstName);
        customer.setSurname(surname);
        customer.setDateOfBirth(dob);

        OcsUser ocsUser = new OcsUser();

        ocsUser.setUsername(username);
        ocsUser.setPassword(password);
        ocsUser.setAuthorities(singletonList(new SimpleGrantedAuthority(STANDARD_CUSTOMER_ROLE.getLabel())));

        customer.setOcsUser(ocsUser);
        customer.setAccountNumber(accountNumber.toUpperCase());

        return customer;
    }
}
