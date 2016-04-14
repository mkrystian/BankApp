package com.luxoft.cjp.april16.bankapp.model;

/**
 * Created by KMajewski on 2016-04-12.
 */
public enum Gender {
    MALE("Mr. "),
    FEMALE("Mrs. ") ;

    private String salutation;

    Gender(String salutation){
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
