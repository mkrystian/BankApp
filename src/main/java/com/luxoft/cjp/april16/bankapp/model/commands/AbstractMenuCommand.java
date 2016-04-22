package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractMenuCommand implements Command {

    private Scanner scanner = new Scanner(System.in);
    //private List<Command> abstractMenuCommands = new ArrayList<>();
    private Command currentCommand;
    private Map<String, Command> abstractMenuCommandsMap = new LinkedHashMap<>();


    void addCommand(String name, Command command) {
        abstractMenuCommandsMap.put(name, command);
    }

    void menu() {
        BankCommander.back = false;
        while (!BankCommander.back) {
            System.out.println("-----------------------------------------------------------------");
            BankCommander.back = false;
            /*Iterator<Command> iterator = abstractMenuCommands.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                System.out.print(i + ") ");
                iterator.next().printCommandInfo();
                i++;
            }*/
            int i = 0;
            for (Map.Entry<String, Command> entrySet : abstractMenuCommandsMap.entrySet()) {
                System.out.println(i + ") " + entrySet.getKey());
                i++;
            }

            String commandString = scanner.nextLine();
            if (commandString.trim().matches("^[0-9]+$")) {
                int command = Integer.parseInt(commandString);
                if (command < abstractMenuCommandsMap.size()) {
                    //abstractMenuCommandsMap.get(command).execute();
                    i = 0;
                    for (Iterator<String> it = abstractMenuCommandsMap.keySet().iterator(); it.hasNext() && i <= command; i++) {
                        if (i == command) {
                            abstractMenuCommandsMap.get(it.next()).execute();
                        } else {
                            it.next();
                        }
                    }

                } else {
                    System.out.println("Please choose number from range [ 0 - " + abstractMenuCommandsMap.size() + "]");
                }
            } else {
                System.out.println("Please choose number from range [ 0 - " + abstractMenuCommandsMap.size() + "]");
            }
        }

    }
}
