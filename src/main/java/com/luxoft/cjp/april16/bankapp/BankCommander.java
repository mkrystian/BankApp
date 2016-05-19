package com.luxoft.cjp.april16.bankapp;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.commands.Command;
import com.luxoft.cjp.april16.bankapp.model.commands.MainMenuCommand;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAO;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAOImpl;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.BankNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;


public class BankCommander {
    public static Bank currentBank = new Bank("MyBank");
    public static BankService bankService = new BankServiceImpl();
    public static Client currentClient;
    public static Command currentCommand;
    private static BankDAO bankDAO = new BankDAOImpl();

    public static void main(String args[]) {

        try {
            currentBank = bankDAO.getBankByName("Database Test Bank");
        } catch (DAOException | BankNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.dir"));
        //BankApplication.initialize(currentBank, bankService);
        currentCommand = new MainMenuCommand();
        while (true) {
            currentCommand.execute();
            try {
                bankDAO.save(currentBank);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }
}





