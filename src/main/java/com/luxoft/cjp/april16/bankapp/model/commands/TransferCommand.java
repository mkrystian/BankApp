package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
class TransferCommand extends AbstractMenuCommand {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        if (BankCommander.currentClient == null) {
            System.out.println("No current client selected");
            return;
        }

        if (BankCommander.currentClient.getActiveAccount() == null) {
            System.out.println("No active account selected for client");
            return;
        }

        System.out.println("Amount of cash to transfer:");

        float amount;
        String inboundValue = scanner.nextLine();

        if (!inboundValue.matches("^[0-9]+$") && !inboundValue.matches("^[0-9]+\\.[0-9]{1,2}$")) {
            System.out.println(" Incorrect amount - interrupted");
            return;
        }
        amount = Float.parseFloat(inboundValue);

        System.out.println("Destination account id:");

        String commandString = scanner.nextLine();
        if (commandString.trim().matches("^[0-9]+$")) {
            int accountId = Integer.parseInt(commandString);
            Account account = null;

            for (Client clientVal : BankCommander.bankService.getClients(BankCommander.currentBank)) {
                for (Account val : clientVal.getAccounts()) {
                    if (val.getId() == accountId) account = val;
                }
            }
            if (account != null) {
                try {
                    BankCommander.currentClient.getActiveAccount().transferTo(account, amount);
                } catch (NotEnoughFoundsException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Transfer completed");
            } else {
                System.out.println("Account not found");
            }
        } else {
            System.out.println("Incorrect data format");
        }
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Transfer money from active account");
    }
}
