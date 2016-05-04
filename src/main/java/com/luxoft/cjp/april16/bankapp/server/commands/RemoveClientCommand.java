package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;
import com.luxoft.cjp.april16.bankapp.service.exceptions.ClientNotFoundByPeselException;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class RemoveClientCommand extends AbstractRemoteOfficeCommand {
    public RemoveClientCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(RORequest request) {
        Response response = new Response();
        try {
            Client client = getClient(request);
            bankService.removeClient(bank, client);
            response.setType(ResponseType.SUCCESS);
            response.setMessage("Client removed: " + client.toString());

        } catch (ClientNotFoundByPeselException e) {
            response.setType(ResponseType.FAIL);
            response.setMessage("Client not found");
        }

        return response;
    }
}
