package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.server.messages.requests.RORequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.ResponseType;
import com.luxoft.cjp.april16.bankapp.service.BankService;

import java.util.List;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-04.
 */
public class FindClientByNameCommand extends AbstractRemoteOfficeCommand {
    public FindClientByNameCommand(Bank bank, BankService bankService) {
        super(bank, bankService);
    }

    @Override
    public Response execute(RORequest request) {
        Response response = new Response();
        response.setType(ResponseType.SUCCESS);
        List<Client> clients = bankService.getClientsByName(bank, request.getData()[0]);

        StringBuilder output = new StringBuilder();

        for (Client element : clients) {
            output.append(element.toString()).append("\n");
        }
        response.setMessage(output.toString());

        return response;
    }
}
