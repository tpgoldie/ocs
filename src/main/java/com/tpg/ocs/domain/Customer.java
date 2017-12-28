package com.tpg.ocs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Customer {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String accountNumber;

    @Override
    public String toString() {
        return String.format("%s %s (%s)", firstName, surname, accountNumber);
    }
}
