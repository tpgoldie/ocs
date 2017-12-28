package com.tpg.ocs.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PersistentName {

    private String firstName;

    private String surname;
}
