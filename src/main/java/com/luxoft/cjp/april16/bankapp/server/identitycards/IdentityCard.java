package com.luxoft.cjp.april16.bankapp.server.identitycards;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class IdentityCard implements Serializable {

    private IdentityType type;
    private String name;

    public IdentityCard(IdentityType identityType, String name) {
        this.type = identityType;
        this.name = name;
    }

    public IdentityType getType() {
        return type;
    }

    public void setType(IdentityType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
