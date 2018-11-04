package de.mieterBewertung.userManagement;

import de.mieterBewertung.database.DatabaseConnection;
import de.mieterBewertung.database.exception.UnkownException;
import de.mieterBewertung.database.exception.UnkownPassword;
import de.mieterBewertung.database.exception.UnkownUser;
import de.mieterBewertung.database.exception.UserAlreadyExisting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCRUD {

    private static final Logger LOGGER = LogManager.getLogger(UserCRUD.class);
    private Connection con;

    public UserCRUD() {
        this.con = DatabaseConnection.createConnection("localhost","3306","evaluationDatabase","root","ttsl/110");
        if(con == null) {
            throw new RuntimeException("Database connection could not be established");
        }
    }

    public void loginUser(User user) throws UnkownUser, UnkownPassword, UnkownException {
        if(!isExisting(user.getUsername())) {
            throw new UnkownUser("User existiert nicht");
        }
        try {
            PreparedStatement getUser = con.prepareStatement("SELECT * FROM evaluationPortal.user WHERE username LIKE ?", Statement.RETURN_GENERATED_KEYS);
            getUser.setString(1, user.getUsername());
            ResultSet resultSet = getUser.executeQuery();
            if(resultSet.next()) {
                String password = resultSet.getString("password");
                if(password.equals(user.getPassword())) {
                    user.setId(resultSet.getInt("id"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));
                    return;
                } else {
                    throw new UnkownPassword("Logindaten stimmen nicht");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UnkownException("Unbekannter Fehler aufgetreten. Bitte erneut versuchen.");
    }

    private boolean isExisting(String username) {
        try {
            PreparedStatement getUser = con.prepareStatement("SELECT * FROM evaluationPortal.user WHERE username LIKE ?");
            getUser.setString(1, username);
            return getUser.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createUser(User user) throws UnkownException, UserAlreadyExisting {
        if(isExisting(user.getUsername())) {
            throw new UserAlreadyExisting("User existiert bereits.");
        }
        try {
            PreparedStatement createUser = con.prepareStatement("INSERT INTO evaluationPortal.user" +
                    "(username, password, email, role) VALUES" +
                    "(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());
            createUser.setString(3, user.getEmail());
            createUser.setString(4, user.getRole().toString());
            int result = createUser.executeUpdate();
            if(result == 1) {
                try (ResultSet generatedKeys = createUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        return;
                    }
                    else {
                        throw new UnkownException("Creating user failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UnkownException("Erstellen des Users fehlgeschlagen. Bitte erneut versuchen.");
    }

    public boolean close() {
        try {
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws UnkownUser, UnkownPassword, UnkownException {
        UserCRUD userCRUD = new UserCRUD();
        User lars = new User("lars","12345","", Role.Admin, "");
        User julian = new User("julian","12345","", Role.LandLord, "");
        userCRUD.loginUser(lars);
    }
}
