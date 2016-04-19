package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Client;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FindClientCommand extends AbstractMenuCommand {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        System.out.println("Client name like: ");
        String commandString = scanner.nextLine();
        List<Client> list = BankCommander.bankService.getClients(BankCommander.currentBank).stream().filter(val -> val.getName().toLowerCase().matches(".*" + commandString.toLowerCase() + ".*")).collect(Collectors.toList());

        System.out.println("Results:");
        if (list.isEmpty()) System.out.println("No results found");
        for (Client val : list) {
            System.out.println(val.toString());
        }
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Find Client");
    }
}