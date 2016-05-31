package com.luxoft.cjp.april16.bankapp.server.identitycards;

import java.io.Serializable;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-28.
 */
public class IdentityCard implements Serializable {

    private final IdentityType type;

    public IdentityCard(IdentityType identityType) {
        this.type = identityType;
    }

    public IdentityType getType() {
        return type;
    }

}
