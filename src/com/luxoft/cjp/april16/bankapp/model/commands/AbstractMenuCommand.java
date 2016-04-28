package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractMenuCommand implements Command{

    private Scanner scanner = new Scanner(System.in);
    private List<AbstractMenuCommand> abstractMenuCommands = new ArrayList<>();
    private int command;



    void addCommand(AbstractMenuCommand abstractMenuCommand) {
        abstractMenuCommands.add(abstractMenuCommand);
    }

    void menu() {
        BankCommander.back = false;
        while (!BankCommander.back) {
            System.out.println("-----------------------------------------------------------------");
            BankCommander.back = false;
            Iterator<AbstractMenuCommand> iterator = abstractMenuCommands.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                System.out.print(i + ") ");
                iterator.next().printCommandInfo();
                i++;
            }
            //int command =
            String commandString = scanner.nextLine();
            if (commandString.trim().matches("^[0-9]+$")) {
                command = Integer.parseInt(commandString);
                if (command < abstractMenuCommands.size()) {
                    abstractMenuCommands.get(command).execute();
                } else {
                    System.out.println("Please choose number from range [ 0 - " + abstractMenuCommands.size() + "]");
                }
            } else {
                System.out.println("Please choose number from range [ 0 - " + abstractMenuCommands.size() + "]");
            }
        }

    }
}