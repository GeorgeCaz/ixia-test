package com.xia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter@Setter
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;

	private String firstName;
	private String lastName;
	private String emailId;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "current_id")
	private CurrentAccount currentAccount;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "saving_id")
	private SavingAccount savingAccount;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "debit_id")
	private DebitAccount debitAccount;


	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public User(String firstName, String lastName, String emailId,
                CurrentAccount currentAccount, SavingAccount savingAccount, DebitAccount debitAccount) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.currentAccount=currentAccount;
		this.savingAccount=savingAccount;
		this.debitAccount=debitAccount;
	}

}
