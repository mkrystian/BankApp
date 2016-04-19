package com.luxoft.cjp.april16.bankapp.model.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class BackCommand implements Command {
    @Override
    public void execute() {
        BankCommander.back = true;
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Back");
    }
}
