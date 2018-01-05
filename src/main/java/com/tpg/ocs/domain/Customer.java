package com.tpg.ocs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Customer {

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String surname;

    @JsonProperty
    private String accountNumber;

    public void setAccountNumber(String value) {
        accountNumber = value.toUpperCase();
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", firstName, surname, accountNumber);
    }
}
