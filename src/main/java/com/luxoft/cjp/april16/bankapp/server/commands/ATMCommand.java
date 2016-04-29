package com.luxoft.cjp.april16.bankapp.server.commands;

import com.luxoft.cjp.april16.bankapp.server.messages.requests.ATMRequest;
import com.luxoft.cjp.april16.bankapp.server.messages.responses.Response;

public interface ATMCommand {
    Response execute(ATMRequest request);
}