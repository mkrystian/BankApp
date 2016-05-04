package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class CloseConnectionATMCommand extends AbstractATMCommand {
    public CloseConnectionATMCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(ATMRequest request) {
        Response response = new Response();
        response.setType(ResponseType.CLOSE_CONNECTION);
        response.setCloseConnection(true);
        System.out.println("Connection closed by ATM client");
        return response;
    }
}
