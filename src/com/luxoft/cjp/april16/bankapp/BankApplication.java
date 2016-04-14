package com.luxoft.cjp.april16.bankapp;

import com.luxoft.cjp.april16.bankapp.model.*;
import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;

class BankApplication {

    private static final BankService bankService = new BankServiceImpl();
    private static final Bank bank = new Bank();
    private static Client[] clients = new Client[5];
    private static Account[] accounts = new Account[10];

    public static void main(String[] args) {
        initialize();
        printBankReport();
        modifyBank();
        printBankReport();
    }

    public static void initialize() {

        //bank.addClientRegistrationListener(new PrintClientListener());
        clients[0] = new Client("Adam Nowak", Gender.MALE, 2500);
        clients[1] = new Client("Jan Kowalski", Gender.MALE);
        clients[2] = new Client("Joanna Szeliga", Gender.FEMALE, 4000);
        clients[3] = new Client("Piotr Baltroczyk", Gender.MALE);
        clients[4] = new Client("Piaty Klient", Gender.FEMALE);


        bankService.addClient(bank, clients[3]);
        for (Client val : clients) bankService.addClient(bank, val);

        accounts[0] = new SavingAccount(2000);
        accounts[1] = new SavingAccount(1000);
        accounts[2] = new SavingAccount(1400);
        accounts[3] = new SavingAccount(2100);
        accounts[4] = new SavingAccount(2000);

        accounts[5] = new CheckingAccount(2000);
        accounts[6] = new CheckingAccount(1000);
        accounts[7] = new CheckingAccount(1400);
        accounts[8] = new CheckingAccount(2100);
        accounts[9] = new CheckingAccount(2000);

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

    public static void modifyBank() {
        accounts[0].deposit(1000);
        accounts[3].deposit(1300);
        accounts[5].deposit(1000);
        accounts[8].deposit(1300);


        try {
            accounts[1].withdraw(3000);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
        try {
            accounts[2].withdraw(2100);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
        try {
            accounts[4].withdraw(1200);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
        try {
            accounts[6].withdraw(3000);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
        try {
            accounts[7].withdraw(2100);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
        try {
            accounts[9].withdraw(1200);
        } catch (NotEnoughFoundsException e) {
            System.out.println("Not enough cash to withdraw. " + e.getInfo());
        }
    }

    public static void printBankReport() {
        bank.printReport();
    }

}
