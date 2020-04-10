package com.xia.model.dto;

import com.xia.model.CurrentAccount;
import com.xia.model.DebitAccount;
import com.xia.model.SavingAccount;
import com.xia.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Getter@Setter
public class UserDto implements Serializable {

    private long userId;

    private String firstName;
    private String lastName;
    private String emailId;

    private CurrentAccount currentAccount;
    private SavingAccount savingAccount;
    private DebitAccount debitAccount;

    private List<Transaction> transactions;
}
