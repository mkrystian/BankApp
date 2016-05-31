package com.luxoft.cjp.april16.bankapp.model;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-19.
 */
@SuppressWarnings("unused")
public class ClientTest {

    @Test
    public void hashCodeTest() {

        Client client1, client2, client3;
        client1 = new Client("Jim Carry", Gender.FEMALE, "91023910293", 0, "mail@bc.com", "New York");
        client2 = new Client("Tom Cruise", Gender.FEMALE, "78124201478", 0, "mail@bc.com", "New York");
        client3 = new Client("Jana Tiffany", Gender.FEMALE, "78124201478", 0, "mail@bc.com", "New York");

        Assert.assertThat(client1.hashCode(), not(client2.hashCode()));
        Assert.assertEquals(client2.hashCode(), client3.hashCode());
        Assert.assertEquals(client1.hashCode(), client1.hashCode());
        Assert.assertEquals(client2.hashCode(), client2.hashCode());
    }

    @Test
    public void equalsTest() {

        Client client1, client2, client3;
        client1 = new Client("Jim Carry", Gender.FEMALE, "91023910293", 0, "mail@bc.com", "New York");
        client2 = new Client("Tom Cruise", Gender.FEMALE, "78124201478", 0, "mail@bc.com", "New York");
        client3 = new Client("Jana Tiffany", Gender.FEMALE, "78124201478", 0, "mail@bc.com", "New York");

        Assert.assertEquals(client1.equals(client2), false);
        Assert.assertEquals(client2.equals(client3), true);
    }

}