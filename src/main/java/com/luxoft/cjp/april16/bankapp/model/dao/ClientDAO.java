package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.ClientNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.util.Set;

interface ClientDAO extends BaseDAO {
    /**
     * Return client by its name, initialize client accounts.
     *
     * //@param bank
     * //@param name
     * //@return
     */
    Client findClientByName(Bank bank, String name) throws DAOException, ClientNotFoundException;

    /**
     * Returns the list of all clients of the Bank
     * And their accounts
     * <p>
     * //@param bankId
     *
     * //@return
     */

    Set<Client> getAllClients(Bank bank) throws DAOException;

    /**
     * Method should insert new Client (if id == null)
     * Or update client in database
     *
     * //@param client
     */

    void save(Client client, Bank bank) throws DAOException;

    /**
     * Method removes client from Database
     *
     * //@param client
     */
    void remove(Client client) throws DAOException;
}