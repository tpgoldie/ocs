package com.tpg.ocs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tpg.ocs.client.GrantedAuthorityDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@EqualsAndHashCode
public final class OcsUser {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
    private List<GrantedAuthority> authorities;

    @Override
    public String toString() {
        return username;
    }
}
