package com.tpg.ocs.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static java.util.Collections.emptyList;

@Getter
@JsonRootName("userAuthentication")
public final class FailedUserAuthentication implements UserDetails {

    private static final FailedUserAuthentication INSTANCE = new FailedUserAuthentication();

    public static UserDetails failedUserAuthentication() {
        return INSTANCE;
    }

    private FailedUserAuthentication() {}

    @JsonProperty
    private final String username = "";

    @JsonProperty
    private final String password = "";

    @JsonProperty
    private final List<GrantedAuthority> authorities = emptyList();

    @JsonProperty
    private final boolean accountNonExpired = false;

    @JsonProperty
    private final boolean accountNonLocked = false;

    @JsonProperty
    private final boolean credentialsNonExpired = false;

    public boolean isEnabled() {
        return false;
    }
}
