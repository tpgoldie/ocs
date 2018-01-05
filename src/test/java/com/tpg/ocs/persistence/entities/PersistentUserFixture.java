package com.tpg.ocs.persistence.entities;

import com.tpg.ocs.domain.UserRole;

import java.util.List;

public interface PersistentUserFixture {
    default PersistentUser aPersistentUser(String username, String password, List<UserRole> userRoles) {

        PersistentUser user = new PersistentUser();

        user.setUsername(username);
        user.setPassword(password);

        user.setUserRoles(userRoles);

        return user;
    }
}
