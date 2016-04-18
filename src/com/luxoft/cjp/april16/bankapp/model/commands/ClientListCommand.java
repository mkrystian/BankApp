package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Client;

import java.util.Scanner;

public class ClientListCommand implements Command {

    private Scanner scanner = new Scanner(System.in);

    private Command commands[] = {new SetCurrentClientCommand(),
            new FindClientCommand(),
            new AddClientCommand(),
            new RemoveClientCommand(),
            new BackCommand()

    };

    @Override
    public void execute() {

        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        System.out.println("ID|NAME|GENDER|PESEL");
        //Iterator<Client> iterator = bankService.getClients(currentBank).iterator();

        //Collections.sort( bankService.getClients(currentBank));
        for (Client val : BankCommander.bankService.getClients(BankCommander.currentBank)) {
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
        System.out.println("Clients list");
    }
}