package com.luxoft.cjp.april16.bankapp;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.commands.MainMenuCommand;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;


public class BankCommander {
    public static Bank currentBank = new Bank("MyBank");
    public static BankService bankService = new BankServiceImpl();
    public static Client currentClient;

    public static boolean back = false;



    public static void main(String args[]) {

        BankApplication.initialize(currentBank, bankService);
        new MainMenuCommand().execute();
    }
}





