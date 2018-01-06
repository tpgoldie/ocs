package com.tpg.ocs.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "customers", schema = "ocs")
@Getter
@Setter
@SequenceGenerator(name = "seq_generator", sequenceName = "ocs.customers_seq", allocationSize = 1)
public class CustomerEntity extends PersistenceEntity {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="firstName",
            column=@Column(name="first_name")),
        @AttributeOverride(name="surname",
            column=@Column(name="surname"))
    })
    @Valid
    private PersistentName name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="username",
            column=@Column(name="user_name"))
    })
    @Valid
    private PersistentUser user;

    @Column(name = "account_number")
    @NotEmpty
    private String accountNumber;

    @Column(name = "date_of_birth")
    @NotNull
    private LocalDate dateOfBirth;

    @Column(name = "opened_date")
    @NotNull
    private LocalDate dateOpened;
}
