package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAO;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAOImpl;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.BankNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.util.Scanner;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-19.
 */
class DBSelectBankCommand implements Command {

    private Scanner scanner = new Scanner(System.in);
    private BankDAO bankDAO = new BankDAOImpl();

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();
        String inboundValue = scanner.nextLine();

        try {
            BankCommander.currentBank = bankDAO.getBankByName(inboundValue);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (BankNotFoundException e) {
            System.out.println("Bank not found - Interupted!");
        }

    }

    @Override
    public void printCommandInfo() {
        System.out.println("Select Bank");
    }
}
