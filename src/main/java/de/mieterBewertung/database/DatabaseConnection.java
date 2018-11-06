package de.mieterBewertung.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class);

    public static Connection createConnection(String host, String port, String database, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://"+host+":"+port+"/"+database, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
