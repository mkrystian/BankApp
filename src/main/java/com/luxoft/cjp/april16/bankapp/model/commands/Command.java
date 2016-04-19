package com.luxoft.cjp.april16.bankapp.model.commands;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-19.
 */
public interface Command {

    void execute();

    void printCommandInfo();
}
