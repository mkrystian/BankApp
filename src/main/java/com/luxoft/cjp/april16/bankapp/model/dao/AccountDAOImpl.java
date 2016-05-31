package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.*;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.ConnectionNotFoundException;
import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;


/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-18.
 */
class AccountDAOImpl extends BaseDAOImpl implements AccountDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);
    @Override
    public void save(Account account, Bank bank, Client client) throws DAOException {
        if (account.getId() == -1) {
            insertAccount(account, bank, client);
        } else {
            updateAccount(account);
        }
    }

    private void updateAccount(Account account) throws DAOException {
        String sql = "UPDATE DB_ACCOUNT SET ACCOUNT_BALANCE = ? , ACCOUNT_TYPE = ? , ACCOUNT_OVERDRAFT = ? WHERE ACCOUNT_ID = ?";
        PreparedStatement stmt;

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, account.getBalance());
            stmt.setString(2, account.getType().toString());
            if (account.getType() == AccountType.CHECKING_ACCOUNT) {
                stmt.setFloat(3, ((CheckingAccount) account).getOverdraft());
            } else {
                stmt.setNull(3, Types.DOUBLE);
            }

            stmt.setFloat(4, account.getId());
            stmt.executeUpdate();
            conn.commit();
            logger.debug("Account updated in DB: ", account.toString());


        } catch (SQLException e) {
            logger.error("Cannot update account in DB: ", account.toString(), e);
            throw new DAOException();
        } finally {
            closeConnection();
        }

    }

    private void insertAccount(Account account, Bank bank, Client client) throws DAOException {
        String sql = "INSERT INTO DB_ACCOUNT (ACCOUNT_BANK_CLIENT_ID, " +
                "ACCOUNT_TYPE, " +
                "ACCOUNT_BALANCE, " +
                "ACCOUNT_OVERDRAFT) " +
                "VALUES ( ? , ? , ? , ? )";

        PreparedStatement stmt;

        final int bankClientConnection = provideBankClientConnection(bank, client);

        try {
            openConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, bankClientConnection);
            stmt.setString(2, account.getType().toString());
            stmt.setFloat(3, account.getBalance());
            if (account instanceof CheckingAccount) {
                stmt.setFloat(4, ((CheckingAccount) account).getOverdraft());
            } else {
                stmt.setNull(4, Types.DOUBLE);
            }


            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();

            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            } else {
                throw new DAOException();
            }
            conn.commit();
            logger.debug("Account added to DB: " + account.toString());

        } catch (SQLException e) {
            logger.error("Cannot insert account to DB: ", account.toString(), e);
            throw new DAOException();
        } finally {
            closeConnection();
        }

    }

    private int provideBankClientConnection(Bank bank, Client client) throws DAOException {
        int connection;
        try {
            connection = findBankClientConnection(bank, client);
            logger.debug("Found Bank - Client relation, id: ", connection);
        } catch (ConnectionNotFoundException e) {
            connection = createBankClientConnection(bank, client);
            logger.debug("Bank - Client connection not found, created id: ", connection);
        }
        return connection;
    }

    private int createBankClientConnection(Bank bank, Client client) throws DAOException {
        //language=H2
        String sql = "INSERT INTO DB_BANK_CLIENT (BANK_CLIENT_BANK_ID, BANK_CLIENT_CLIENT_ID) VALUES ( ? , ? )";
        PreparedStatement stmt;
        int connection = 0;

        try {
            openConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, bank.getId());
            stmt.setInt(2, client.getId());

            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();

            if (resultSet.next()) {
                connection = resultSet.getInt(1);
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

        return connection;
    }

    private int findBankClientConnection(Bank bank, Client client) throws ConnectionNotFoundException, DAOException {
        String sql = "SELECT BANK_CLIENT_ID FROM DB_BANK_CLIENT WHERE BANK_CLIENT_BANK_ID = ? AND BANK_CLIENT_ID = ?";
        PreparedStatement stmt;
        int connection = 0;

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getId());
            stmt.setInt(2, client.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                connection = rs.getInt("BANK_CLIENT_ID");
            } else {
                throw new ConnectionNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();

        } finally {
            closeConnection();

        }

        return connection;

    }


    @Override
    public void remove(Account account) throws DAOException {
        String sql = "DELETE FROM DB_ACCOUNT WHERE ACCOUNT_ID = (?)";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, account.getId());
            stmt.execute();
            conn.commit();
            logger.debug("Account deleted from DB: " + account.toString());

        } catch (SQLException e) {
            logger.error("Cannot delete account: ", account.toString(), e);
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Set<Account> getClientAccounts(Client client) throws DAOException {
        String sql = "SELECT ACCOUNT_ID," +
                "ACCOUNT_BANK_CLIENT_ID," +
                "ACCOUNT_TYPE," +
                "ACCOUNT_BALANCE," +
                "ACCOUNT_OVERDRAFT" +
                " FROM DB_ACCOUNT" +
                " JOIN DB_BANK_CLIENT ON ACCOUNT_BANK_CLIENT_ID = DB_BANK_CLIENT.BANK_CLIENT_ID " +
                "WHERE BANK_CLIENT_CLIENT_ID = ?";

        PreparedStatement stmt;
        Set<Account> accountList = new TreeSet<>();

        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, client.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                accountList.add(AccountType.factoryMethod(rs.getString("ACCOUNT_TYPE"))
                        .buildAccount(rs.getFloat("ACCOUNT_BALANCE"),
                                rs.getFloat("ACCOUNT_OVERDRAFT"),
                                rs.getInt("ACCOUNT_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }

        return accountList;
    }
}
