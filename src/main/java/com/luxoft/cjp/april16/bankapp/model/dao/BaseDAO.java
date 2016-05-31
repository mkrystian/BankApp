package com.luxoft.cjp.april16.bankapp.model.dao;

import com.luxoft.cjp.april16.bankapp.model.dao.exceptions.DAOException;

import java.sql.Connection;

interface BaseDAO {
    @SuppressWarnings("unused")
    Connection openConnection() throws DAOException;

    @SuppressWarnings("unused")
    void closeConnection();
}