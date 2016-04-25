package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Account;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class AccountsListMenuCommand extends AbstractMenuCommand {

    public AccountsListMenuCommand(MainMenuCommand mainMenuCommand) {
        super.registerCommand(0, new SetActiveAccountCommand());
        super.registerCommand(1, mainMenuCommand);

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
            System.out.println("ID|BALANCE|ACCOUNT TYPE|OVERDRAFT");
        }

        for (Account val : BankCommander.bankService.getAccounts(BankCommander.currentClient)) {
            if (val == BankCommander.currentClient.getActiveAccount()) System.out.print("(active)");
            System.out.println(val.toString());
        }

        menu();


    }

    @Override
    public void printCommandInfo() {
        System.out.println("Accounts list of current client");
    }
}
