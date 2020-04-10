package com.xia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "CURRENT")
@Getter@Setter
public class CurrentAccount {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "acc_type")
    private String accType;

    @JsonIgnore
    @OneToOne(mappedBy = "currentAccount")
    private User user;

    @Column(name = "balance")
    private int balance;

}
