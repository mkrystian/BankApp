package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Account;
import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.Gender;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.ClientNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
public class ClientDAOImpl extends BaseDAOImpl implements ClientDAO {

    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public Client findClientByName(Bank bank, String name) throws DAOException, ClientNotFoundException {
        String sql = "SELECT CLIENT_ID, CLIENT_NAME, CLIENT_PESEL, CLIENT_GENDER, CLIENT_INITIAL_OVERDRAFT, CLIENT_EMAIL, CLIENT_CITY, CLIENT_ACTIVE_ACCOUNT_ID FROM DB_CLIENT JOIN DB_BANK_CLIENT ON DB_CLIENT.CLIENT_ID = DB_BANK_CLIENT.BANK_CLIENT_CLIENT_ID WHERE CLIENT_NAME=? AND BANK_CLIENT_BANK_ID=?";
        PreparedStatement stmt;
        final Client client;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, bank.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = new Client(rs.getString("CLIENT_NAME"), Gender.factoryMethod(rs.getString("CLIENT_GENDER")), rs.getString("CLIENT_PESEL"), rs.getInt("CLIENT_INITIAL_OVERDRAFT"), rs.getString("CLIENT_EMAIL"), rs.getString("CLIENT_CITY"), rs.getInt("CLIENT_ID"));
            } else {
                throw new ClientNotFoundException(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }

        return client;
    }

    @Override
    public Set<Client> getAllClients(Bank bank) throws DAOException {
        String sql = "SELECT CLIENT_ID, CLIENT_NAME, CLIENT_PESEL, CLIENT_GENDER, CLIENT_INITIAL_OVERDRAFT, CLIENT_EMAIL, CLIENT_CITY, CLIENT_ACTIVE_ACCOUNT_ID FROM DB_CLIENT JOIN DB_BANK_CLIENT ON DB_CLIENT.CLIENT_ID = DB_BANK_CLIENT.BANK_CLIENT_CLIENT_ID WHERE BANK_CLIENT_BANK_ID=?";
        PreparedStatement stmt;
        Set<Client> clientList = new TreeSet<>();

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client tmpClient = new Client(rs.getString("CLIENT_NAME"), Gender.factoryMethod(rs.getString("CLIENT_GENDER")), rs.getString("CLIENT_PESEL"), rs.getInt("CLIENT_INITIAL_OVERDRAFT"), rs.getString("CLIENT_EMAIL"), rs.getString("CLIENT_CITY"), rs.getInt("CLIENT_ID"));
                clientList.add(tmpClient);
                for (Account account : accountDAO.getClientAccounts(tmpClient)) {
                    tmpClient.addAccount(account);
                    if (account.getId() == rs.getInt("CLIENT_ACTIVE_ACCOUNT_ID")) tmpClient.setActiveAccount(account);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }

        return clientList;
    }

    @Override
    public void save(Client client, Bank bank) throws DAOException {
        if (client.getId() == -1) {
            insertClient(client);
        } else {
            updateClient(client);

        }

        for (Account account : client.getAccounts()) {
            accountDAO.save(account, bank, client);
        }


    }

    private void updateClient(Client client) throws DAOException {
        String sql = "UPDATE DB_CLIENT SET CLIENT_NAME = ?, CLIENT_PESEL = ?, CLIENT_GENDER = ?, CLIENT_INITIAL_OVERDRAFT = ?, CLIENT_EMAIL = ?, CLIENT_CITY = ?, CLIENT_ACTIVE_ACCOUNT_ID = ? WHERE CLIENT_ID = ?";
        PreparedStatement stmt;

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPesel());
            stmt.setString(3, client.getGender().toString());
            stmt.setFloat(4, client.getInitialOverdraft());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getCity());
            if (client.getActiveAccount() != null && client.getActiveAccount().getId() != -1) {
                stmt.setInt(7, client.getActiveAccount().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            stmt.setInt(8, client.getId());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }


    private void insertClient(Client client) throws DAOException {
        String sql = "INSERT INTO DB_CLIENT (CLIENT_NAME, CLIENT_PESEL, CLIENT_GENDER, CLIENT_INITIAL_OVERDRAFT, CLIENT_EMAIL, CLIENT_CITY, CLIENT_ACTIVE_ACCOUNT_ID) VALUES ( ? , ? , ? , ? , ? , ? , ? )";
        PreparedStatement stmt;

        try {
            openConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPesel());
            stmt.setString(3, client.getGender().toString());
            stmt.setFloat(4, client.getInitialOverdraft());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getCity());
            if (client.getActiveAccount() != null && client.getActiveAccount().getId() != -1) {
                stmt.setInt(7, client.getActiveAccount().getId());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }

            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();

            if (resultSet.next()) {
                client.setId(resultSet.getInt(1));
            } else {
                throw new DAOException();
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void remove(Client client) throws DAOException {
        String sql = "DELETE FROM DB_CLIENT WHERE CLIENT_ID = (?)";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, client.getId());
            stmt.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }
}
