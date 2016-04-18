package com.luxoft.cjp.april16.bankapp;

import com.luxoft.cjp.april16.bankapp.model.*;
import com.luxoft.cjp.april16.bankapp.model.commands.*;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;

import java.util.Scanner;


public class BankCommander {
    public static Bank currentBank = new Bank("MyBank");
    public static BankService bankService = new BankServiceImpl();
    public static Client currentClient;

    public static boolean back = false;
    static Command[] commands = {



            // etc.

    };

    private static void initialize() {

        Client[] clients = new Client[5];
        Account[] accounts = new Account[10];
        //bank.addClientRegistrationListener(new PrintClientListener());
        clients[0] = new Client("Adam Nowak", Gender.MALE, "78021298512", 2500);
        clients[1] = new Client("Jan Kowalski", Gender.MALE, "78041298513");
        clients[2] = new Client("Joanna Szeliga", Gender.FEMALE, "90121398512", 4000);
        clients[3] = new Client("Piotr Baltroczyk", Gender.MALE, "92121391212");
        clients[4] = new Client("Piaty Klient", Gender.FEMALE, "89121398213");


        BankCommander.bankService.addClient(BankCommander.currentBank, clients[3]);
        for (Client val : clients) BankCommander.bankService.addClient(BankCommander.currentBank, val);

        accounts[0] = new SavingAccount(2000);
        accounts[1] = new SavingAccount(1000);
        accounts[2] = new SavingAccount(1400);
        accounts[3] = new SavingAccount(2100);
        accounts[4] = new SavingAccount(2000);

        accounts[5] = new CheckingAccount(2000, 1000);
        accounts[6] = new CheckingAccount(1000, 100);
        accounts[7] = new CheckingAccount(1400, 3000);
        accounts[8] = new CheckingAccount(2100, 2000);
        accounts[9] = new CheckingAccount(2000, 1000);

        clients[0].addAccount(accounts[0]);
        clients[0].addAccount(accounts[1]);
        clients[0].addAccount(accounts[2]);
        clients[0].addAccount(accounts[3]);
        clients[0].addAccount(accounts[4]);

        clients[1].addAccount(accounts[5]);
        clients[1].addAccount(accounts[6]);
        clients[1].addAccount(accounts[7]);
        clients[1].addAccount(accounts[8]);
        clients[1].addAccount(accounts[9]);

    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        initialize();


        new MainMenuCommand().execute();

    }
}





