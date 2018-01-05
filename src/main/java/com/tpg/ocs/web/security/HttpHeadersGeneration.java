package com.tpg.ocs.web.security;

import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public interface HttpHeadersGeneration extends BasicAuthenticationTokens {

    default HttpHeaders generateHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(AUTHORIZATION, createBasicAuthentication("ocs_anon_user", "ocs_new_customer"));
        httpHeaders.add(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);

        return httpHeaders;
    }
}
