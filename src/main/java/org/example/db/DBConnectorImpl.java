package org.example.db;

import org.example.System.PropertiesTaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectorImpl implements DBConnector {
    private static final String DRIVER_NAME = "driver_name";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static DBConnector instance;

    DBConnectorImpl() {
    }

    public static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnectorImpl();
            loadDriver(PropertiesTaker.getProperties(DRIVER_NAME));
        }
        return instance;
    }

    static void loadDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер неверный");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(PropertiesTaker.getProperties(URL), PropertiesTaker.getProperties(USERNAME), PropertiesTaker.getProperties(PASSWORD)
        );
    }

}
