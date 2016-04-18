package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Account;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class AccountsListCommand extends Command {

    public AccountsListCommand() {
        //super.addCommand( new SetActiveAccount());
        //super.addCommand( new AddAccountCommand());
        //super.addCommand( new RemoveAccountCommand());
        super.addCommand(new BackCommand());

    }



    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        if (BankCommander.currentClient == null) {
            System.out.println("No current client selected");
            return;
        }


        if (BankCommander.currentClient.getAccounts().isEmpty()) {
            System.out.println("No created accounts for current client");
        } else {
            System.out.println("ACCOUNT TYPE|ID|BALANCE|OVERDRAFT");
        }

        for (Account val : BankCommander.currentClient.getAccounts()) {
            System.out.println(val.toString());
        }

        int command;
        menu();


    }

    @Override
    public void printCommandInfo() {
        System.out.println("Accounts list of current client");
    }
}
