package com.luxoft.cjp.april16.bankapp.model.commands;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-18.
 */
public class MainMenuCommand extends AbstractMenuCommand {

    public MainMenuCommand() {
        super.addCommand(new ClientListMenuCommand());
        super.addCommand(new AccountsListMenuCommand());
        super.addCommand(new WithdrawCommand());
        super.addCommand(new DepositCommand());
        super.addCommand(new TransferCommand());
        super.addCommand(new AddClientCommand());
        super.addCommand(new Command() {
            public void execute() {
                System.exit(0);
            }

            public void printCommandInfo() {
                System.out.println("Exit");
            }
        });


    }

    @Override
    public void execute() {
        while (true) menu();

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Main menu");
    }
}
