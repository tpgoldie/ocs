package com.tpg.ocs.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Setter
@JsonRootName("userAuthentication")
public class UserAuthentication implements UserDetails {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)
    private List<GrantedAuthority> authorities;

    @JsonProperty
    private boolean accountNonExpired;

    @JsonProperty
    private boolean accountNonLocked;

    @JsonProperty
    private boolean credentialsNonExpired;

    public boolean isEnabled() {
        return accountNonLocked && accountNonExpired && credentialsNonExpired;
    }
}
