package com.tpg.ocs.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers", schema = "ocs")
@Getter
@Setter
@SequenceGenerator(name = "seq_generator", sequenceName = "customer_seq", allocationSize = 1)
public class CustomerEntity extends PersistenceEntity {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="firstName",
            column=@Column(name="first_name")),
        @AttributeOverride(name="surname",
            column=@Column(name="surname"))
    })
    private PersistentName name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="username",
            column=@Column(name="user_name"))
    })
    private PersistentUser user;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
