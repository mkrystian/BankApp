package com.luxoft.cjp.april16.bankapp.server.messages.requests;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class ATMRequest extends Request {

    ATMRequestType requestType;

    public ATMRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(ATMRequestType requestType) {
        this.requestType = requestType;
    }
}
