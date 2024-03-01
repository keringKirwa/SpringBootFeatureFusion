package com.voting.votingsystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    private String firstName;
    private String lastName;
    private int age;
    private String region;/*This is meant  for primary partitioning of the bank accounts*/
    private Date accountExpiryDate; /*meant for secondary partitioning*/
    private String bankId;
    private int accountBalance;

}
