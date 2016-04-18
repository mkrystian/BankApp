package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Account;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class AccountsListCommand implements Command {

    private Command commands[] = { //new SetActiveAccount(),
            //new SetActiveAccount(),
            //new AddAccountCommand(),
            //new RemoveAccountCommand(),
            new BackCommand()

    };

    private Scanner scanner = new Scanner(System.in);

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
        while (!BankCommander.back) {
            System.out.println("-----------------------------------------------------------------");
            BankCommander.back = false;
            for (int i = 0; i < commands.length; i++) { // show menu2
                System.out.print(i + ") ");
                commands[i].printCommandInfo();
            }
            //int command =
            String commandString = scanner.nextLine();
            if (commandString.trim().matches("^[0-9]+$")) {
                command = Integer.parseInt(commandString);
                if (command < commands.length) {
                    commands[command].execute();
                } else {
                    System.out.println("Please choose number from range [ 0 - " + commands.length + "]");
                }
            } else {
                System.out.println("Please choose number from range [ 0 - " + commands.length + "]");
            }


        }


    }

    @Override
    public void printCommandInfo() {
        System.out.println("Accounts list of current client");
    }
}
