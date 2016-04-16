package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.OverDraftLimitExceededException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-16.
 */
public class CheckingAccountTest {

    @Test
    public void withdraw() throws Exception {
        AbstractAccount checkingAccount = new CheckingAccount(1000, 500);
        Assert.assertEquals("Balance in checkingAccount is incorrect", checkingAccount.getBalance(), 1000, 0);
    }


    @Test
    public void deposit() throws Exception {

    }

    @Test
    public void testCheckingAccountIllegalArgumentException() throws Exception {
        try {
            new CheckingAccount(-200, 1000);
            Assert.fail("Exception  IllegalArgumentException expected");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new CheckingAccount(400, -1000);
            Assert.fail("Exception  IllegalArgumentException expected");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new CheckingAccount(-200, -1000);
            Assert.fail("Exception  IllegalArgumentException expected");
        } catch (IllegalArgumentException ignored) {
        }

    }

    @Test
    public void testWithdrawOverDraftLimitExceededException() throws Exception {

        CheckingAccount checkingAccount = new CheckingAccount(1500, 200);

        try {
            checkingAccount.withdraw(1800f);
            Assert.fail("Exception OverDraftLimitExceededException expected");
        } catch (OverDraftLimitExceededException e) {
            Assert.assertEquals(e.getBalance(), checkingAccount.getBalance(), 0);
            Assert.assertEquals(e.getAmount(), 1800f, 0);
            Assert.assertEquals(e.getAccount(), checkingAccount);
            Assert.assertEquals(e.getOverdraft(), checkingAccount.getOverdraft(), 0);
        }

        try {
            checkingAccount.withdraw(1600f);
        } catch (OverDraftLimitExceededException e) {
            Assert.fail("Exception OverDraftLimitExceededException shouldn't appear ");
        }

    }

}