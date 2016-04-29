package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;

public class GetBalanceCommand extends AbstractATMCommand {

    public GetBalanceCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(ATMRequest request) {

        Response newResponse = new Response();
        Client client;
        try {
            client = super.getClient(request);
            newResponse.setType(ResponseType.SUCCESS);
            newResponse.setMessage(Float.toString(client.getBalance()));
        } catch (ClientNotFoundByPeselException e) {
            newResponse.setType(ResponseType.FAIL);
            newResponse.setMessage(e.getMessage());
        }


        return newResponse;
    }
}