package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class ClientDAOImpl extends BaseDAOImpl implements ClientDAO {
    @Override
    public Client findClientByName(Bank bank, String name) throws DAOException {
        String sql = "SELECT CLIENT_ID, CLIENT_NAME, CLIENT_PESEL, CLIENT_GENDER, CLIENT_INITIAL_OVERDRAFT, CLIENT_EMAIL, CLIENT_CITY, CLIENT_ACTIVE_ACCOUNT_ID FROM DB_CLIENT JOIN DB_BANK_CLIENT ON DB_CLIENT.CLIENT_ID = DB_BANK_CLIENT.BANK_CLIENT_CLIENT_ID WHERE CLIENT_NAME=? AND BANK_CLIENT_BANK_ID=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, bank.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                //TODO: Read from database
                //new Client(rs.)
            } else {
                //TODO: Create new exception
                //throw new ClientNotFoundException(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        throw new UnsupportedOperationException("ClientDAOImpl not implemented");
        //return null;
    }

    @Override
    public List<Client> getAllClients(Bank bank) throws DAOException {
        throw new UnsupportedOperationException("ClientDAOImpl not implemented");
    }

    @Override
    public void save(Client client) throws DAOException {
        throw new UnsupportedOperationException("ClientDAOImpl not implemented");
    }

    @Override
    public void remove(Client client) throws DAOException {
        throw new UnsupportedOperationException("ClientDAOImpl not implemented");
    }
}
