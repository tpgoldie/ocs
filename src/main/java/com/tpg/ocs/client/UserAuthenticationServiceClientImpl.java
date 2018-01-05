package com.tpg.ocs.client;

import com.tpg.ocs.domain.OcsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.tpg.ocs.client.FailedUserAuthentication.failedUserAuthentication;
import static com.tpg.ocs.client.OcsAnonymousUser.OCS_ANONYMOUS_PASSWORD;
import static com.tpg.ocs.client.OcsAnonymousUser.OCS_ANONYMOUS_USER;

@Component
public class UserAuthenticationServiceClientImpl implements UserAuthenticationServiceClient {

    private static Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationServiceClientImpl.class);

    private static final String BASE_URL = "http://localhost:8181/ocs/users";

    private static boolean isOcsAnonymousUser(String username) {
        return OCS_ANONYMOUS_USER.equals(username);
    }

    private RestTemplate restTemplate;

    @Autowired
    public UserAuthenticationServiceClientImpl(RestTemplateBuilder restTemplateBuilder) {

        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("Checking user authentication ...");

        try {

            UsernameAndPassword usernameAndPassword = isOcsAnonymousUser(username)
                ? new UsernameAndPassword(username, OCS_ANONYMOUS_PASSWORD)
                : new UsernameAndPassword(username, "");

            HttpEntity<UsernameAndPassword> request = new HttpEntity<>(usernameAndPassword);

            String url = String.format("%s/%s", BASE_URL, username);

            UserAuthentication userAuthentication = restTemplate.postForObject(url, request, UserAuthentication.class);

            LOGGER.info("User authentication received.");

            return userAuthentication;
        }
        catch (Exception e) {
            LOGGER.error("Failed to authenticate user", e);
            return failedUserAuthentication();
        }
    }

    @Override
    public Optional<OcsUser> createUser(String username, String password) {

        UsernameAndPassword usernameAndPassword = new UsernameAndPassword(username, password);

        HttpHeaders httpHeaders = generateHttpHeaders();

        HttpEntity<UsernameAndPassword> request = new HttpEntity<>(usernameAndPassword, httpHeaders);

        OcsUser user = restTemplate.postForObject(BASE_URL, request, OcsUser.class);

        return Optional.of(user);
    }
}
