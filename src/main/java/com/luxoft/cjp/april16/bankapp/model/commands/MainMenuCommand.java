package com.luxoft.cjp.april16.bankapp.model.commands;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-18.
 */
public class MainMenuCommand extends AbstractMenuCommand {

    public MainMenuCommand() {
        super.addCommand("Clients List", new ClientListMenuCommand());
        super.addCommand("Accounts list", new AccountsListMenuCommand());
        super.addCommand("Withdraw money", new WithdrawCommand());
        super.addCommand("Deposit money", new DepositCommand());
        super.addCommand("Transfer money from current account", new TransferCommand());
        super.addCommand("Add client", new AddClientCommand());
        super.addCommand("Exit", new Command() {
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
