package com.tpg.ocs.web.security;

import org.springframework.util.Base64Utils;

public interface BasicAuthenticationTokens {

    default String createBasicAuthentication() {
        return "Basic " + Base64Utils.encodeToString("user:secret".getBytes());
    }
}
