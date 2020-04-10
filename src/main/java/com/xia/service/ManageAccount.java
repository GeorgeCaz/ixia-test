package com.xia.service;

import com.xia.model.dto.UserDto;

public interface ManageAccount {

    void transferMoneyBetweenAcc(String sourceAcc, String destinationAcc, int amount, long userId);
    UserDto findById(long userId);
}
