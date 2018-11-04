package de.mieterBewertung.product;

import de.mieterBewertung.database.DatabaseConnection;
import de.mieterBewertung.userManagement.UserCRUD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class ProductCRUD {

    private static final Logger LOGGER = LogManager.getLogger(UserCRUD.class);
    private Connection con;

    public ProductCRUD() {
        this.con = DatabaseConnection.createConnection("localhost","3306","evaluationDatabase","root","ttsl/110");
        if(con == null) {
            throw new RuntimeException("Database connection could not be established");
        }
    }
}
