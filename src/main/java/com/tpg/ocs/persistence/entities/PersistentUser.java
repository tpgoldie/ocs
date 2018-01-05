package com.tpg.ocs.persistence.entities;

import com.tpg.ocs.domain.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.List;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class PersistentUser {

    private String username;

    private String password;

    @Transient
    private List<UserRole> userRoles;

    @Override
    public String toString() {
        return username;
    }
}
