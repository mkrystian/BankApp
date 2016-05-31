package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.commands.AbstractRemoteOfficeCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.FindClientByNameCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.GetStatisticsCommand;
import com.luxoft.cjp.april16.bankapp.server.commands.RemoveClientCommand;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public enum RORequestType implements Serializable {
    GET_STATISTICS {
        @Override
        public AbstractRemoteOfficeCommand getCommand(Bank bank, BankService bankService) {
            return new GetStatisticsCommand(bank, bankService);
        }
    },
    FIND_CLIENT_BY_NAME {
        @Override
        public AbstractRemoteOfficeCommand getCommand(Bank bank, BankService bankService) {
            return new FindClientByNameCommand(bank, bankService);
        }
    },
    REMOVE_CLIENT {
        @Override
        public AbstractRemoteOfficeCommand getCommand(Bank bank, BankService bankService) {
            return new RemoveClientCommand(bank, bankService);
        }
    };


    public abstract AbstractRemoteOfficeCommand getCommand(Bank bank, BankService bankService);
}
