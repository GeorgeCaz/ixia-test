package com.xia.model;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TransferDto {
    private String sourceAcc;
    private String destinationAcc;
    private int amount;
}
