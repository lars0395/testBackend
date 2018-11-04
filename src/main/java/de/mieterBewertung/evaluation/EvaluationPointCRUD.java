package de.mieterBewertung.evaluation;

import de.mieterBewertung.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvaluationPointCRUD {

    private Connection connection;

    public EvaluationPointCRUD() {
        this.connection = DatabaseConnection.createConnection("localhost","3306","evaluationDatabase", "root","ttsl/110");
    }

    public List<EvaluationPoint> getEvaluationPoints() throws SQLException {
        List<EvaluationPoint> list = new ArrayList<>();
        try {
            PreparedStatement getAll = connection.prepareStatement("SELECT * FROM evaluationPoint;", Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = getAll.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String descirption = resultSet.getString("description");
                EvaluationPoint temp = new EvaluationPoint(name, descirption);
                temp.setId(resultSet.getInt("id"));
                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
