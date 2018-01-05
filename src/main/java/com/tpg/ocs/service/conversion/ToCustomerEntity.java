package com.tpg.ocs.service.conversion;

import com.tpg.ocs.domain.NewCustomer;
import com.tpg.ocs.domain.UserRole;
import com.tpg.ocs.persistence.entities.CustomerEntity;
import com.tpg.ocs.persistence.entities.PersistentName;
import com.tpg.ocs.persistence.entities.PersistentUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tpg.ocs.domain.UserRole.matchUserRole;

public interface ToCustomerEntity extends ToEntity<NewCustomer, CustomerEntity> {
    default CustomerEntity convert(NewCustomer source) {

        CustomerEntity entity = new CustomerEntity();

        PersistentName name = setName(source);

        entity.setName(name);

        PersistentUser user = new PersistentUser();
        user.setUsername(source.getOcsUser().getUsername());
        user.setPassword(source.getOcsUser().getPassword());

        List<UserRole> userRoles = source.getOcsUser().getAuthorities()
                .stream().map(auth -> matchUserRole(auth.getAuthority()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

        user.setUserRoles(userRoles);

        entity.setUser(user);

        entity.setAccountNumber(source.getAccountNumber());

        return entity;
    }

    default PersistentName setName(NewCustomer customer) {

        PersistentName name = new PersistentName();

        name.setFirstName(customer.getFirstName());
        name.setSurname(customer.getSurname());

        return name;
    }
}
