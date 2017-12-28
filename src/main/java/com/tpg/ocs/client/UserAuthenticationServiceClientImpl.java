package com.tpg.ocs.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserAuthenticationServiceClientImpl implements UserAuthenticationServiceClient {

    private static Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationServiceClientImpl.class);

    private RestTemplate restTemplate;

    @Autowired
    public UserAuthenticationServiceClientImpl(RestTemplateBuilder restTemplateBuilder) {

        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("Checking user authentication ...");

        ResponseEntity<UserAuthentication> response = restTemplate.getForEntity(String.format("/users/%s", username), UserAuthentication.class);

        LOGGER.info("User authentication received.");

        return response.getBody();
    }
}
