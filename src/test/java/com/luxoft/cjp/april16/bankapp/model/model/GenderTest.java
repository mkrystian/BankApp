package com.luxoft.cjp.april16.bankapp.model.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-16.
 */
public class GenderTest {

    @Test
    public void testGetSalutation() throws Exception {

        Assert.assertEquals("Incorrect FEMALE salutation", Gender.FEMALE.getSalutation(), "Mrs. ");
        Assert.assertEquals("Incorrect MALE salutation", Gender.MALE.getSalutation(), "Mr. ");

    }


}