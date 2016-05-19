package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.Bank;
import com.luxoft.cjp.april16.bankapp.model.BankInfo;
import com.luxoft.cjp.april16.bankapp.model.Client;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.BankNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.ClientNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;
import com.luxoft.cjp.april16.bankapp.model.exceptions.ClientExistsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BankDAOImpl extends BaseDAOImpl implements BankDAO {

    private ClientDAO clientDAO = new ClientDAOImpl();

    public Bank getBankByName(String name) throws DAOException, BankNotFoundException {
        Bank bank = new Bank(name);
        String sql = "SELECT BANK_ID, BANK_NAME FROM DB_BANK WHERE BANK_NAME=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("BANK_ID");
                bank.setId(id);
            } else {
                throw new BankNotFoundException(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }

        for (Client client : clientDAO.getAllClients(bank)) {
            try {
                bank.addClient(client);
            } catch (ClientExistsException e) {
                throw new DAOException();
            }
        }


        return bank;


    }

    @Override
    public void save(Bank bank) throws DAOException {
        if (bank.getId() == -1) {
            insertBank(bank);
        } else {
            updateBank(bank);
        }

        for (Client client : bank.getClients()) {
            clientDAO.save(client, bank);
        }

    }

    private void updateBank(Bank bank) throws DAOException {
        String sql = "UPDATE DB_BANK SET BANK_NAME = (?) WHERE BANK_ID = ?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bank.getName());
            stmt.setInt(2, bank.getId());
            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    private void insertBank(Bank bank) throws DAOException {
        String sql = "INSERT INTO DB_BANK (BANK_NAME) VALUES (?)";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bank.getName());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();

            if (resultSet.next()) {
                bank.setId(resultSet.getInt(1));
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
    public void remove(Bank bank) throws DAOException {
        String sql = "DELETE FROM DB_BANK WHERE BANK_ID = (?)";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());
            stmt.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    @Override
    public BankInfo getBankInfo(Bank bank) throws DAOException {
        BankInfo bankInfo = new BankInfo();
        bankInfo.setNumberOfClients(getNumberOfClients(bank));
        bankInfo.setTotalAccountSum(getTotalAccountSum(bank));
        bankInfo.setClientsByCity(getClientsByCity(bank));
        return bankInfo;
    }

    private Map<String, List<Client>> getClientsByCity(Bank bank) throws DAOException {
        String sql = "SELECT CLIENT_CITY , CLIENT_NAME " +
                "  FROM DB_CLIENT" +
                "    JOIN DB_BANK_CLIENT ON BANK_CLIENT_CLIENT_ID = DB_CLIENT.CLIENT_ID" +
                "    WHERE BANK_CLIENT_BANK_ID = ?" +
                "  GROUP BY CLIENT_NAME";
        PreparedStatement stmt;

        Map<String, List<Client>> map = new TreeMap<>();

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());
            stmt.execute();

            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                if (!map.containsKey(resultSet.getString("CLIENT_CITY"))) {
                    map.put(resultSet.getString("CLIENT_CITY"), new ArrayList<>());
                }
                map.get(resultSet.getString("CLIENT_CITY")).add(clientDAO.findClientByName(bank, resultSet.getString("CLIENT_NAME")));
            }

        } catch (SQLException | ClientNotFoundException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }

        return map;
    }

    private float getTotalAccountSum(Bank bank) throws DAOException {
        String sql = "SELECT SUM(ACCOUNT_BALANCE)" +
                "  FROM DB_ACCOUNT" +
                "    JOIN DB_BANK_CLIENT ON BANK_CLIENT_ID = DB_ACCOUNT.ACCOUNT_BANK_CLIENT_ID" +
                "  WHERE BANK_CLIENT_BANK_ID = ?";
        PreparedStatement stmt;
        float totalAmount = 0;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());
            stmt.execute();

            ResultSet resultSet = stmt.getResultSet();

            if (resultSet.next()) {
                totalAmount = resultSet.getInt(1);
            } else {
                throw new DAOException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return totalAmount;
    }


    private int getNumberOfClients(Bank bank) throws DAOException {
        String sql = "SELECT COUNT(*) FROM DB_BANK_CLIENT WHERE BANK_CLIENT_BANK_ID = ?";
        PreparedStatement stmt;
        int numberOfClients = 0;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());
            stmt.execute();

            ResultSet resultSet = stmt.getResultSet();

            if (resultSet.next()) {
                numberOfClients = resultSet.getInt(1);
            } else {
                throw new DAOException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return numberOfClients;
    }
}