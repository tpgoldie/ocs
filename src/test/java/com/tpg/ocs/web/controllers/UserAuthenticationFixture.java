package com.tpg.ocs.web.controllers;

import com.tpg.ocs.client.UserAuthentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.tpg.ocs.domain.UserRole.NEW_CUSTOMER_ROLE;
import static java.util.Collections.singletonList;

public interface UserAuthenticationFixture {

    default UserAuthentication ocsAnonymousUserNewCustomerUserAuthentication() {

        UserAuthentication userAuthentication = new UserAuthentication();

        userAuthentication.setUsername("ocs_anon_user");
        userAuthentication.setPassword("ocs_new_customer");
        userAuthentication.setAccountNonExpired(true);
        userAuthentication.setAccountNonLocked(true);
        userAuthentication.setCredentialsNonExpired(true);
        userAuthentication.setAuthorities(singletonList(new SimpleGrantedAuthority(NEW_CUSTOMER_ROLE.getRole())));

        return userAuthentication;
    }

    default UserAuthentication johnDoeUserAuthentication() {

        UserAuthentication userAuthentication = new UserAuthentication();

        userAuthentication.setUsername("jdoe");
        userAuthentication.setPassword("ABC-123");
        userAuthentication.setAccountNonExpired(true);
        userAuthentication.setAccountNonLocked(true);
        userAuthentication.setCredentialsNonExpired(true);

        return userAuthentication;
    }
}
