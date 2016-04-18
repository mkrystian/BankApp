package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public abstract class Command {

    private Scanner scanner = new Scanner(System.in);
    private List<Command> commands = new ArrayList<>();
    private int command;

    public abstract void execute();

    public abstract void printCommandInfo();

    void addCommand(Command command) {
        commands.add(command);
    }

    void menu() {
        BankCommander.back = false;
        while (!BankCommander.back) {
            System.out.println("-----------------------------------------------------------------");
            BankCommander.back = false;
            Iterator<Command> iterator = commands.iterator();
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
                if (command < commands.size()) {
                    commands.get(command).execute();
                } else {
                    System.out.println("Please choose number from range [ 0 - " + commands.size() + "]");
                }
            } else {
                System.out.println("Please choose number from range [ 0 - " + commands.size() + "]");
            }
        }

    }
}
