package com.tpg.ocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tpg.ocs.client.OcsAnonymousUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NewCustomer extends Customer implements OcsAnonymousUser {

    @JsonProperty
    private OcsUser ocsUser = new OcsUser();

    @JsonIgnore
    public String getUsername() { return ocsUser.getUsername(); }

    @JsonIgnore
    public String getPassword() { return ocsUser.getPassword(); }

}
