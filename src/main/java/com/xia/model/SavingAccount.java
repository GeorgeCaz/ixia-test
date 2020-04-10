package com.xia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SAVING")
@Getter@Setter
public class SavingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acc_type")
    private String accType;

    @JsonIgnore
    @OneToOne(mappedBy = "savingAccount")
    private User user;

    @Column(name = "balance")
    private int balance;

}
