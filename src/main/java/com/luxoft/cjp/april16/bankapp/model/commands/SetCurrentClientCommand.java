package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Client;

import java.util.Scanner;

public class SetCurrentClientCommand extends AbstractMenuCommand {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        System.out.println("Client's id:");
        String commandString = scanner.nextLine();
        if (commandString.trim().matches("^[0-9]+$")) {
            int clientId = Integer.parseInt(commandString);
            Client client = null;

            for (Client val : BankCommander.bankService.getClients(BankCommander.currentBank)) {
                if (val.getId() == clientId) client = val;
            }

            if (client != null) {
                BankCommander.currentClient = client;
                System.out.println("Client is current client now:\n" + client.toString());
            } else {
                System.out.println("Client with id: " + clientId + " doesn't exist");
            }
        } else {
            System.out.println("Incorrect data format");
        }

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Set current client");
    }
}
