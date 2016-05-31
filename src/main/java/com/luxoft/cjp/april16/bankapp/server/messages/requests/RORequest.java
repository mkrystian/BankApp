package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class RORequest extends Request {


    private final RORequestType requestType;

    public RORequest(IdentityCard identityCard, RORequestType roRequestType) {
        super(identityCard);
        this.requestType = roRequestType;
    }

    public RORequestType getRequestType() {
        return requestType;
    }

}
