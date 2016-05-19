package com.luxoft.cjp.april16.bankapp.model.commands;

import com.luxoft.cjp.april16.bankapp.BankCommander;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAO;
import com.luxoft.cjp.april16.bankapp.model.dao.BankDAOImpl;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-18.
 */
class DBBankInfoCommand extends AbstractMenuCommand {

    private BankDAO bankDAO = new BankDAOImpl();

    @Override
    public void execute() {
        System.out.println("-----------------------------------------------------------------");
        printCommandInfo();

        try {
            System.out.println(bankDAO.getBankInfo(BankCommander.currentBank));
        } catch (DAOException e) {
            System.out.println("Error - cannot show report!");
        }


    }

    @Override
    public void printCommandInfo() {
        System.out.println("Bank info");
    }
}
