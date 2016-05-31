package com.luxoft.cjp.april16.bankapp.model;

import com.luxoft.cjp.april16.bankapp.model.exceptions.NotEnoughFoundsException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Bank Application for CJP
 * Created by PREZES on 2016-04-16.
 */
@SuppressWarnings("unused")
public class SavingAccountTest {

    @Test
    public void deposit() {
        AbstractAccount savingAccount = new SavingAccount(10000);
        savingAccount.deposit(2000);
        Assert.assertEquals("Balance in savingAccount is incorrect", savingAccount.getBalance(), 12000f, 0);
    }

    @Test
    public void testWithdraw() throws Exception {
        SavingAccount savingAccount = new SavingAccount(1500);

        savingAccount.withdraw(200);
        Assert.assertEquals("Balance after first withdraw is incorrect", savingAccount.getBalance(), 1300f, 0);
        savingAccount.withdraw(150.25f);
        Assert.assertEquals("Balance after second withdraw is incorrect", savingAccount.getBalance(), 1149.75f, 0);

    }

    @Test
    public void testWithdrawNotEnoughFoundsException() throws Exception {

        SavingAccount savingAccount = new SavingAccount(1500);
        float amount = 1600f;
        try {
            savingAccount.withdraw(amount);
            Assert.fail("Exception  NotEnoughFoundsException expected");
        } catch (NotEnoughFoundsException e) {
            Assert.assertEquals(e.getBalance(), savingAccount.getBalance(), 0);
            Assert.assertEquals(e.getAmount(), amount, 0);
            Assert.assertEquals(e.getAccount(), savingAccount);
        }

    }

    @Test
    public void testSavingAccountIllegalArgumentException() throws Exception {


        try {
            new SavingAccount(-200);
            Assert.fail("Exception  IllegalArgumentException expected");
        } catch (IllegalArgumentException ignored) {
        }

    }

}