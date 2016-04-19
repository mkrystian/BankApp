package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.Client;

public class ClientListCommand extends AbstractMenuCommand {


    public ClientListCommand() {
        super.addCommand(new FindClientCommand());
        super.addCommand(new SetCurrentClientCommand());
        super.addCommand(new AddClientCommand());
        super.addCommand(new RemoveClientCommand());
        super.addCommand(new BackCommand());
    }

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

        menu();

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Clients list");
    }
}