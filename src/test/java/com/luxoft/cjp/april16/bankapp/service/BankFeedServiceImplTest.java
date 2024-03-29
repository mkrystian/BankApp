package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import org.junit.Before;
import org.junit.Test;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-04-27.
 */
public class BankFeedServiceImplTest {

    private BankFeedService bankFeedService;

    @Before
    public void setUp() throws Exception {
        Bank bank = new Bank("MyBank");
        bankFeedService = new BankFeedServiceImpl(bank);
    }

    @Test
    public void loadFeed() throws Exception {
        bankFeedService.loadFeed();
    }

}