package com.tpg.ocs.client;

import org.assertj.core.api.AbstractAssert;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserAuthenticationServiceClientAssertion extends AbstractAssert<UserAuthenticationServiceClientAssertion, UserAuthenticationServiceClient> {
    public static UserAuthenticationServiceClientAssertion assertThat(UserAuthenticationServiceClient userAuthenticationServiceClient) {

        return new UserAuthenticationServiceClientAssertion(userAuthenticationServiceClient);
    }

    UserAuthenticationServiceClientAssertion(UserAuthenticationServiceClient userAuthenticationServiceClient) {
        super(userAuthenticationServiceClient, UserAuthenticationServiceClientAssertion.class);
    }

    public UserAuthenticationServiceClientAssertion createsUser(String username, String password) {

        verify(actual).createUser(username, password);

        return this;
    }

    public UserAuthenticationServiceClientAssertion doesNotCreateUser(String username, String password) {

        verify(actual, never()).createUser(username, password);

        return this;
    }
}
