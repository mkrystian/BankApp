package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.service.BankFeedServiceImpl;

import java.util.Scanner;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-27.
 */
class FeedDataCommand implements Command {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        System.out.println("File/directory location ( if empty: ./clients/):");

        String inboundValue = scanner.nextLine();

        System.out.println(inboundValue);

        if ( inboundValue.length() == 0){
            new BankFeedServiceImpl( BankCommander.currentBank ).loadFeed();
        }else{
            new BankFeedServiceImpl( BankCommander.currentBank, inboundValue ).loadFeed();
        }
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Read data from file/directory");
    }
}
