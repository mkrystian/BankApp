package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class BaseDAOImpl implements BaseDAO {
    Connection conn;

    public Connection openConnection() throws DAOException {
        try {
            Class.forName("org.h2.Driver"); // this is driver for H2
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~\\BankAppJDBC",
                    "sa", // login
                    "" // password
            );
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
        //return null;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}