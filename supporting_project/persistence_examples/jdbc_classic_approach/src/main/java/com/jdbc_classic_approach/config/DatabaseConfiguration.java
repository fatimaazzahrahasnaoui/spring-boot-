package com.jdbc_classic_approach.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConfiguration {
    private static final String URL = "jdbc:mysql://localhost:3306/exampledb";
    private static final String USER = "root";
    private static final String PASSWORD = "DOCTORtemara1079..";

    public static Connection getConnection() throws SQLException { // Changer de protected à public
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
