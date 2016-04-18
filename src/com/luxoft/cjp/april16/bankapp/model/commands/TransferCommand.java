package com.luxoft.cjp.april16.bankapp.model.commands;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
public class TransferCommand extends Command {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Transfer money between accounts");
    }
}
