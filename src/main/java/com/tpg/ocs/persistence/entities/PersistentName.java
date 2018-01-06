package com.tpg.ocs.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PersistentName {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String surname;
}
