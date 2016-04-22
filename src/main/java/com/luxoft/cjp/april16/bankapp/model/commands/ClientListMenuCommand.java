package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Client;

public class ClientListMenuCommand extends AbstractMenuCommand {


    public ClientListMenuCommand() {
        super.addCommand("Find client by name", new FindClientCommand());
        super.addCommand("Set current client", new SetCurrentClientCommand());
        super.addCommand("Add client", new AddClientCommand());
        super.addCommand("Remove client", new RemoveClientCommand());
        super.addCommand("Back", new BackCommand());
    }

    @Override
    public void execute() {

        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        System.out.println("ID|NAME|GENDER|PESEL|INITIAL OVERDRAFT|EMAIL|CITY");
        //Iterator<Client> iterator = bankService.getClients(currentBank).iterator();

        //Collections.sort( bankService.getClients(currentBank));
        for (Client val : BankCommander.bankService.getClients(BankCommander.currentBank)) {
            if (val == BankCommander.currentClient) System.out.print("(active)");
            System.out.println(val.toString());
        }

        menu();

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Clients list");
    }
}