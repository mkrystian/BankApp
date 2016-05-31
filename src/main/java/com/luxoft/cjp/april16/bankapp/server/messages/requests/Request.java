package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public abstract class Request implements Serializable {
    private final IdentityCard identityCard;
    private String[] data;

    Request(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }


    public IdentityType getIdentityType() {
        return identityCard.getType();
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

}
