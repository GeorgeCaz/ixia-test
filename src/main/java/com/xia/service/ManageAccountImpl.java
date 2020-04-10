package com.xia.service;


import com.xia.model.CurrentAccount;
import com.xia.model.User;
import com.xia.model.SavingAccount;
import com.xia.model.Transaction;
import com.xia.model.dto.UserDto;
import com.xia.repository.UserRepository;
import com.xia.repository.TransactionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ManageAccountImpl implements ManageAccount {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public void transferMoneyBetweenAcc(String sourceAcc, String destinationAcc, int amount, long userId) {
        User user = userRepository.getOne(userId);

        Transaction transaction = new Transaction();


        if(sourceAcc.equals("CURRENT")) {
            CurrentAccount currentAccount = user.getCurrentAccount();
            currentAccount.setBalance(currentAccount.getBalance() - amount);
            user.setCurrentAccount(currentAccount);
            transaction.setSourceAccount("CURRENT");
        }

        if(sourceAcc.equals("SAVING")) {
            SavingAccount savingAccount = user.getSavingAccount();
            savingAccount.setBalance(savingAccount.getBalance() - amount);
            user.setSavingAccount(savingAccount);
            transaction.setSourceAccount("SAVING");
        }

        if(destinationAcc.equals("CURRENT")) {
            CurrentAccount currentAccount = user.getCurrentAccount();
            currentAccount.setBalance(currentAccount.getBalance() + amount);
            user.setCurrentAccount(currentAccount);
            transaction.setDestinationAccount("CURRENT");
        }

        if(destinationAcc.equals("SAVING")) {
            SavingAccount savingAccount = user.getSavingAccount();
            savingAccount.setBalance(savingAccount.getBalance() + amount);
            user.setSavingAccount(savingAccount);
            transaction.setDestinationAccount("SAVING");
        }

        transaction.setAmount(amount);
        user.getTransactions().add(transaction);

    }

    @Override
    public UserDto findById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user.get(), UserDto.class);
        return  userDto;
    }
}
