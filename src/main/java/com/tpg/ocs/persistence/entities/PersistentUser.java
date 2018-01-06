package com.tpg.ocs.persistence.entities;

import com.tpg.ocs.domain.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class PersistentUser {

    @Size(min = 3, max = 10)
    private String username;

    @Size(min = 3, max = 10)
    private String password;

    @Transient
    private List<UserRole> userRoles;

    @Override
    public String toString() {
        return username;
    }
}
