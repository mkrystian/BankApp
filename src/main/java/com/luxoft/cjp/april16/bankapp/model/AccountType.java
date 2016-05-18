package com.luxoft.cjp.april16.bankapp.model;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public enum AccountType {

    CHECKING_ACCOUNT("Checking account"),
    SAVING_ACCOUNT("Saving account");

    private String name;

    AccountType(String s) {
        name = s;
    }
}
