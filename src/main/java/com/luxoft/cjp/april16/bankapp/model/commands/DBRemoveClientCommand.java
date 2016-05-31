package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.dao.ClientDAOImpl;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
class DBRemoveClientCommand extends AbstractMenuCommand {

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        if (BankCommander.currentClient == null) {
            System.out.println("No current client selected");
            return;
        }

        try {
            (new ClientDAOImpl()).remove(BankCommander.currentClient);
            BankCommander.bankService.removeClient(BankCommander.currentBank, BankCommander.currentClient);
            System.out.println("Client Removed from DB");
        } catch (DAOException e) {
            System.out.println("Error - client cannot be removed!");
        }


    }

    @Override
    public void printCommandInfo() {
        System.out.println("Remove current client");
    }
}
