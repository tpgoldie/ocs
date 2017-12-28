package com.tpg.ocs.web.controllers;

import com.tpg.ocs.client.UserAuthentication;

public interface UserAuthenticationFixture {

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
