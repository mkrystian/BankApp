package com.luxoft.cjp.april16.bankapp.server.messages.requests;

import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityCard;
import com.luxoft.cjp.april16.bankapp.server.identitycards.IdentityType;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public abstract class Request implements Serializable {
    private String[] data;
    private IdentityCard identityCard;

    private boolean closeConnection = false;

    public Request(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

    public boolean isCloseConnection() {
        return closeConnection;
    }

    public void setCloseConnection(boolean closeConnection) {
        this.closeConnection = closeConnection;
    }



    public IdentityType getIdentityType() {
        return identityCard.getType();
    }

    public IdentityCard getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

}
