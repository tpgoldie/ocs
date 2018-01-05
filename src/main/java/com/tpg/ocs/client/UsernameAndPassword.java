package com.tpg.ocs.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@JsonRootName("userAuthenticationDetails")
public final class UsernameAndPassword {
    @JsonProperty
    private final String username;

    @JsonProperty
    private final String password;

    public UsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
