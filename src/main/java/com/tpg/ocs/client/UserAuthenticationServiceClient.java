package com.tpg.ocs.client;

import com.tpg.ocs.domain.OcsUser;
import com.tpg.ocs.web.security.HttpHeadersGeneration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserAuthenticationServiceClient extends UserDetailsService, HttpHeadersGeneration {
    Optional<OcsUser> createUser(String username, String password);
}
