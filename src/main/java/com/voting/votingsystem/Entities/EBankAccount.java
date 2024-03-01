package com.voting.votingsystem.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_accounts")
public class EBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String emailAddress;
    private int age;
    private String county;
    private int accountExpiryYear;
    private String bankId;
    private int accountBalance;

    /*
    * CREATE TABLE bank_accounts (
    id INT DEFAULT unique_rowid() UNIQUE ,
    first_name VARCHAR(255),
    email_address VARCHAR(255) UNIQUE ,
    age INT,
    county VARCHAR(255),
    account_expiry_year INT,
    account_balance INT,
    PRIMARY KEY (county, id));*/
}
