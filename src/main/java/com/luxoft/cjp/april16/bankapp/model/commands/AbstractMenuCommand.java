package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

abstract class AbstractMenuCommand implements Command {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, Command> commandsMap = new LinkedHashMap<>();


    void registerCommand(int number, Command command) {
        commandsMap.put(number, command);
    }

    void menu() {
        modifyCurrentMenu();
        System.out.println("-----------------------------------------------------------------");
        for (Map.Entry<Integer, Command> entrySet : commandsMap.entrySet()) {
            System.out.print(entrySet.getKey() + ") ");
            entrySet.getValue().printCommandInfo();
        }

        String commandString = scanner.nextLine();
        if (commandString.trim().matches("^[0-9]+$")) {
            int command = Integer.parseInt(commandString);
            if (commandsMap.containsKey(command)) {
                commandsMap.get(command).execute();
            } else {
                System.out.println("Number " + command + " doesn't exist in menu");
            }
        } else {
            System.out.println("Please choose number from menu");
        }
    }

    private void modifyCurrentMenu() {
        BankCommander.currentCommand = this;
    }
}