package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Account;

import java.util.Scanner;

public class SetActiveAccountCommand extends AbstractMenuCommand {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        System.out.println("Account id:");

        if (BankCommander.currentClient == null) {
            System.out.println("No current client selected");
            return;
        }

        String commandString = scanner.nextLine();
        if (commandString.trim().matches("^[0-9]+$")) {
            int accountId = Integer.parseInt(commandString);
            Account account = null;


            for (Account val : BankCommander.bankService.getAccounts(BankCommander.currentClient)) {
                if (val.getId() == accountId) account = val;
            }

            if (account != null) {
                BankCommander.currentClient.setActiveAccount(account);
                System.out.println("Active account set to:\n" + account.toString());
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("Incorrect data format");
        }

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Set active account");
    }
}
