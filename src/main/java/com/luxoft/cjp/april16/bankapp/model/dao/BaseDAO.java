package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.sql.Connection;

public interface BaseDAO {
    Connection openConnection() throws DAOException;

    void closeConnection();
}