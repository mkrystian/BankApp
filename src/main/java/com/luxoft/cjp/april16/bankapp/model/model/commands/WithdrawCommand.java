package com.luxoft.cjp.april16.bankapp.model.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.model.exceptions.NotEnoughFoundsException;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class WithdrawCommand extends AbstractMenuCommand {

    private Scanner scanner = new Scanner(System.in);

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

        System.out.println("Amount of cash to withdraw:");

        float amount;
        String inboundValue = scanner.nextLine();

        if (!inboundValue.matches("^[0-9]+$") || !inboundValue.matches("^[0-9]+.[0-9]{1,2}$")) {
            System.out.println(" Incorrect amount - interrupted");
            return;
        }

        amount = Float.parseFloat(inboundValue);

        try {
            BankCommander.currentClient.getActiveAccount().withdraw(amount);
        } catch (NotEnoughFoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Withdraw money from current account");
    }
}
