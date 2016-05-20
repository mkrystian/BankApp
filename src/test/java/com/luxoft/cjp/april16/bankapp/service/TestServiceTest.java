package com.luxoft.cjp.april16.bankapp.service;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-20.
 */
public class TestServiceTest {


    private Bank mockBank1;
    private Bank mockBank2;
    private Bank mockBank3;
    private Client mockClient;
    private BankService bankService = new BankServiceImpl();
    private Map<String, List<Client>> mockBank2ClientsMap;

    @Before
    public void setUp() throws Exception {
        mockBank1 = new Bank("Test Bank");
        mockBank2 = new Bank("Test Bank");
        mockBank3 = new Bank("Test Bank3");
        mockClient = new Client("Jana Jenkins", Gender.MALE, "34292302923", 0, "dsd@wsa.pl", "Budapest");

        Field mockBank2ClientsMapField = mockBank2.getClass().getDeclaredField("clientsMap");
        mockBank2ClientsMapField.setAccessible(true);

        //noinspection unchecked
        mockBank2ClientsMap = (Map<String, List<Client>>) mockBank2ClientsMapField.get(mockBank2);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void isEquals() throws Exception {
        // Test of two identical objects
        assertThat(TestService.isEquals(mockBank1, mockBank2), is(true));
        // Modification of field with @NoDB annotation
        mockBank2ClientsMap.put("TestValue", null);
        assertThat(TestService.isEquals(mockBank1, mockBank2), is(true));
        // Modification of field without @NoBD annotation
        mockBank2.addClient(mockClient);
        assertThat(TestService.isEquals(mockBank1, mockBank2), is(false));
        // Test of two objects with diferent field name (without annotation)
        assertThat(TestService.isEquals(mockBank1, mockBank3), is(false));

        // Test of two objects of two diferent classes
        assertThat(TestService.isEquals(mockBank1, bankService), is(false));
    }


}