package com.tpg.ocs.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public interface OcsUserDomainFixture {

    default OcsUser anOcsUser(String username, String password, List<UserRole> userRoles) {

        List<GrantedAuthority> authorities = userRoles.stream().map(ur -> new SimpleGrantedAuthority(ur.getLabel())).collect(Collectors.toList());

        OcsUser user = new OcsUser();

        user.setUsername(username);
        user.setPassword(password);
        user.setAuthorities(authorities);

        return user;
    }
}
