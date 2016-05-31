package com.luxoft.cjp.april16.bankapp;

import com.luxoft.cjp.april16.bankapp.model.*;
import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import com.luxoft.cjp.april16.bankapp.service.BankReport;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.BankServiceImpl;

public class BankApplication {

    public static final Bank bank = new Bank("MyBank");
    private static final BankService bankService = new BankServiceImpl();
    private static final Client[] clients = new Client[5];
    private static final Account[] accounts = new Account[10];

    public static void main(String[] args) {
        initialize(bank, bankService);

        if (args.length > 0 && args[0].equals("report")) {
            new BankReport().printReport();
            return;
        }
        printBankReport();
        modifyBank();
        printBankReport();
    }

    public static void initialize(Bank bank, BankService bankService) {

        clients[0] = new Client("Tom Cruise", Gender.MALE, "78021298512", 2500, "mymail@abc.com", "New York");
        clients[1] = new Client("Elton John", Gender.MALE, "78041298513", 0, "nomail@rocks.com", "Washington");
        clients[2] = new Client("Carla Willis", Gender.FEMALE, "90121398512", 4000, "jana@abc.com", "New Jersey");
        clients[3] = new Client("Leonardo Dicaprio", Gender.MALE, "92121391212", 1000, "balti@abc.com", "London");
        clients[4] = new Client("Jena Philips", Gender.FEMALE, "89121398213", 500, "mailtance@abc.com", "London");


        for (Client val : clients) bankService.addClient(bank, val);

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

        bankService.addAccount(clients[0], accounts[0]);
        bankService.addAccount(clients[1], accounts[1]);
        bankService.addAccount(clients[2], accounts[2]);
        bankService.addAccount(clients[3], accounts[3]);
        bankService.addAccount(clients[4], accounts[4]);

        bankService.addAccount(clients[0], accounts[5]);
        bankService.addAccount(clients[1], accounts[6]);
        bankService.addAccount(clients[2], accounts[7]);
        bankService.addAccount(clients[3], accounts[8]);
        bankService.addAccount(clients[4], accounts[9]);

    }

    private static void modifyBank() {
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

    private static void printBankReport() {
        bank.printReport();
    }

}
