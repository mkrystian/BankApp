package com.luxoft.cjp.april16.bankapp.model.commands;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-18.
 */
public class MainMenuCommand extends AbstractMenuCommand {

    public MainMenuCommand() {
        super.registerCommand(0, new ClientListMenuCommand(this));
        super.registerCommand(1, new AccountsListMenuCommand(this));
        super.registerCommand(2, new WithdrawCommand());
        super.registerCommand(3, new DepositCommand());
        super.registerCommand(4, new TransferCommand());
        super.registerCommand(5, new AddClientCommand());
        super.registerCommand(6, new FeedDataCommand());
        super.registerCommand(7, new DBSelectBankCommand());
        super.registerCommand(8, new DBRemoveClientCommand());
        super.registerCommand(9, new DBBankInfoCommand());
        super.registerCommand(10, new Command() {
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
        menu();

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Main menu");
    }
}
