package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class ATMRequest extends Request {

    private ATMRequestType requestType;

    public ATMRequest(IdentityCard identityCard, ATMRequestType atmRequestType) {
        super(identityCard);
        this.requestType = atmRequestType;
    }

    public ATMRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(ATMRequestType requestType) {
        this.requestType = requestType;
    }
}
