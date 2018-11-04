package de.mieterBewertung.evaluation;

import de.mieterBewertung.database.DatabaseConnection;

import java.sql.Connection;

public class EvaluationCRUD {

    private Connection connection;

    public EvaluationCRUD() {
        this.connection = DatabaseConnection.createConnection("localhost","3306","evaluation","root","ttsl/110");
    }

    public void createEvaluation() {

    }
}
