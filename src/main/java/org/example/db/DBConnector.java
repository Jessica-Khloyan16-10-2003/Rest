package org.example.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    Connection getConnection() throws SQLException;
}
