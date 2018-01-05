package com.tpg.ocs.domain;

import com.tpg.ocs.util.UniqueIdGeneration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.tpg.ocs.domain.UserRole.STANDARD_CUSTOMER_ROLE;
import static java.util.Collections.singletonList;

public interface NewCustomerDomainFixture extends UniqueIdGeneration {
    default NewCustomer newCustomerJohnDoe() {
        return newCustomer("John", "Doe", generateId(), "jdoe", "ABC-213");
    }

    default NewCustomer newCustomer(String firstName, String surname, String accountNumber, String username, String password) {

        NewCustomer customer = new NewCustomer();

        customer.setFirstName(firstName);
        customer.setSurname(surname);
        OcsUser ocsUser = new OcsUser();

        ocsUser.setUsername(username);
        ocsUser.setPassword(password);
        ocsUser.setAuthorities(singletonList(new SimpleGrantedAuthority(STANDARD_CUSTOMER_ROLE.getLabel())));

        customer.setOcsUser(ocsUser);
        customer.setAccountNumber(accountNumber.toUpperCase());

        return customer;
    }
}
