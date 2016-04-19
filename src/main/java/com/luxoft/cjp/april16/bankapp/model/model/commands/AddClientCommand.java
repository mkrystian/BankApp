package com.luxoft.cjp.april16.bankapp.model.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.model.Client;
import com.luxoft.cjp.april16.bankapp.model.model.Gender;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class AddClientCommand implements Command {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        String name;
        String pesel;
        Gender gender;

        System.out.print("Client's name [ Name Surname ]: ");
        String inboundValue = scanner.nextLine();

        if (!inboundValue.matches("^[A-Z][a-z]{3,} [A-Z][a-z]{3,}$")) {
            System.out.println("Incorrect name - interrupted");
            return;
        }

        name = inboundValue;

        System.out.print("Client's PESEL: ");
        inboundValue = scanner.nextLine();
        if (!inboundValue.matches("^[0-9]{11}$")) {
            System.out.println("Incorrect PESEL - interrupted");
            return;
        }

        pesel = inboundValue;

        System.out.print("Client's gender [M/F]: ");
        inboundValue = scanner.nextLine();
        if (!inboundValue.toLowerCase().matches("^[fm]$")) {
            System.out.println("Incorrect gender - interrupted");
            return;
        }

        if (inboundValue.toLowerCase().equals("m")) gender = Gender.MALE;
        else gender = Gender.FEMALE;

        Client newClient = new Client(name, gender, pesel);
        BankCommander.bankService.addClient(BankCommander.currentBank, newClient);
        System.out.println("New client added: ");
        System.out.println(newClient.toString());

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Add client");
    }
}



