package com.tpg.ocs.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
@Getter
@Setter
public abstract class PersistenceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator")
    private Long id;
}
