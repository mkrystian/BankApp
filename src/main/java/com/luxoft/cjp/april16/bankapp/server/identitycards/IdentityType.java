package com.luxoft.cjp.april16.bankapp.server.identitycards;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.BankServerATM;
import com.luxoft.cjp.april16.bankapp.server.BankServerInterface;
import com.luxoft.cjp.april16.bankapp.server.BankServerRemoteOffice;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public enum IdentityType implements Serializable {
    ATM {
        @Override
        public BankServerInterface getBankServer(Bank bank, BankService bankService) {
            return new BankServerATM(bank, bankService);
        }
    },
    REMOTE_OFFICE {
        @Override
        public BankServerInterface getBankServer(Bank bank, BankService bankService) {
            return new BankServerRemoteOffice(bank, bankService);
        }
    },
    BANK_SERVER {
        @Override
        public BankServerInterface getBankServer(Bank bank, BankService bankService) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract BankServerInterface getBankServer(Bank bank, BankService bankService);
}
