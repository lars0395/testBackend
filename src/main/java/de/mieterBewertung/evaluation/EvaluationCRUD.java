package de.mieterBewertung.evaluation;

import de.mieterBewertung.database.DatabaseConnection;
import de.mieterBewertung.database.exception.UnkownException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class EvaluationCRUD {

    private Connection connection;

    public EvaluationCRUD() {
        this.connection = DatabaseConnection.createConnection("localhost","3306","evaluationPortal","root","ttsl/110");
    }

    public void createEvaluation(Evaluation evaluation) throws SQLException {
        try {
            PreparedStatement insert = this.connection.prepareStatement("INSERT INTO evaluationPortal.evaluation (" +
                    "firstName, lastName, city, birthDay) VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, evaluation.getFirstName());
            insert.setString(2, evaluation.getLastName());
            insert.setString(3, evaluation.getCity());
            insert.setTimestamp(4, new Timestamp(evaluation.getBirthDay().getTime()));
            int result = insert.executeUpdate();
            if (result == 1) {
                try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        evaluation.setId(generatedKeys.getInt(1));
                    }
                }
                for (EvaluationPoint point: evaluation.getEvaluationPointList()) {
                    insert = this.connection.prepareStatement("INSERT INTO " +
                            "evaluationPortal.evaluation_evaluationPoint (id_evaluation, id_evaluationPoint, `value`) VALUES" +
                            "(?,?,?);", Statement.RETURN_GENERATED_KEYS);
                    insert.setInt(1, evaluation.getId());
                    insert.setInt(2, point.getId());
                    insert.setBoolean(3, point.isValue());
                    insert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
