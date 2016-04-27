package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.IncorrectGenderException;

import java.io.Serializable;

/**
 * Bank Application for CJP
 * Created by KMajewski on 2016-04-12.
 */
public enum Gender implements Serializable{
    MALE("Mr. ", "male"),
    FEMALE("Mrs. ", "female");

    private String salutation;
    private String name;

    Gender(String salutation, String name) {
        this.salutation = salutation;
        this.name = name;
    }

    public static Gender factoryMethod(String gender) {
        if (gender.toLowerCase().equals("f") || gender.toLowerCase().equals("female")) {
            return FEMALE;
        }
        if (gender.toLowerCase().equals("m") || gender.toLowerCase().equals("male")) {
            return MALE;
        }

        throw new IncorrectGenderException(gender + " is not correct gender.");
    }

    public String getSalutation() {
        return salutation;
    }

    public String getName() {
        return name;
    }
}
