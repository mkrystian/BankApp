package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.commands.AbstractATMCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.CloseConnectionATMCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.GetBalanceCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.WithdrawCommand;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public enum ATMRequestType implements Serializable {
    GET_BALANCE {
        @Override
        public AbstractATMCommand getCommand(Bank bank, BankService bankService) {
            return new GetBalanceCommand(bank, bankService);
        }
    },
    WITHDRAW {
        @Override
        public AbstractATMCommand getCommand(Bank bank, BankService bankService) {
            return new WithdrawCommand(bank, bankService);
        }
    },
    CLOSE_CONNECTION {
        @Override
        public AbstractATMCommand getCommand(Bank bank, BankService bankService) {
            return new CloseConnectionATMCommand(bank, bankService);
        }
    };

    public abstract AbstractATMCommand getCommand(Bank bank, BankService bankService);
}
