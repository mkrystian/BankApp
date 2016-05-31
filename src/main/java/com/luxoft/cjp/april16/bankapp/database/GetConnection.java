package com.luxoft.cjp.april16.bankapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * BankApp for CJP
 * Created by KMajewski on 2016-05-16.
 */
class GetConnection {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~\\default", "admin", "")) {
            connection.prepareCall("DROP TABLE DB_ACCOUNT").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
