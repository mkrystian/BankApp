package com.luxoft.cjp.april16.bankapp.model.model;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public enum Gender {
    MALE("Mr. ", "male"),
    FEMALE("Mrs. ", "famale");

    private String salutation;
    private String name;

    Gender(String salutation, String name) {
        this.salutation = salutation;
        this.name = name;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getName() {
        return name;
    }
}
