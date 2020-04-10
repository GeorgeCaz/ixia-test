package com.xia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DEBIT")
@Getter@Setter
public class DebitAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acc_type")
    private String accType;

    @JsonIgnore
    @OneToOne(mappedBy = "debitAccount")
    private User user;

    @Column(name = "balance")
    private int balance;


}
