package com.tpg.ocs.web.security;

import org.springframework.util.Base64Utils;

public interface BasicAuthenticationTokens {

    default String createBasicAuthentication(String username, String password) {
        return "Basic " + Base64Utils.encodeToString(String.format("%s:%s", username, password).getBytes());
    }
}
