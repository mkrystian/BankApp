package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-19.
 */
@SuppressWarnings("unused")
public class ClientDAOImplTest {
    private Client client;
    private ClientDAO clientDAO;
    private Bank mockBank;

    @Before
    public void setUp() {
        clientDAO = new ClientDAOImpl();
        mockBank = Mockito.mock(Bank.class);
        Mockito.when(mockBank.getId()).thenReturn(1);
        mockBank.getClients();
        client = new Client("Test Database Client", Gender.MALE, "99921298512", 2500, "dsf@abc.com", "Test City");
    }

    @Test
    public void findClientByName() throws Exception {
        final Client clientByName = clientDAO.findClientByName(mockBank, "Tom Cruise");
        assertThat(clientByName.toString(), is("1|Tom Cruise|male|78021298512|2500.0|mymail@abc.com|New York"));
    }

    @Test
    public void getAllClients() throws Exception {
        final Set<Client> allClients = clientDAO.getAllClients(mockBank);
        assertThat(allClients.size(), is(4));
    }

    @Test
    public void saveAndRemove() throws Exception {
        clientDAO.save(client, mockBank);
        clientDAO.remove(client);
    }



}