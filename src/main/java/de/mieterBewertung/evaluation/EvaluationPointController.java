package de.mieterBewertung.evaluation;

import com.google.gson.Gson;
import static de.mieterBewertung.ControllerUtils.toJson;
import de.mieterBewertung.database.exception.UnkownException;
import de.mieterBewertung.database.exception.UnkownPassword;
import de.mieterBewertung.database.exception.UnkownUser;
import de.mieterBewertung.userManagement.LoginController;
import de.mieterBewertung.userManagement.User;
import de.mieterBewertung.userManagement.UserSessionMapping;
import de.mieterBewertung.userManagement.exception.AlreadyLoggedInException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class EvaluationPointController {

    private static final Logger LOGGER = LogManager.getLogger(EvaluationPointController.class);
    private EvaluationPointCRUD evaluationPointCRUD;

    private EvaluationPointCRUD getEvaluationPointCRUD() {
        if(evaluationPointCRUD == null) {
            this.evaluationPointCRUD = new EvaluationPointCRUD();
        }
        return this.evaluationPointCRUD;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/evaluationPoints", method = RequestMethod.GET)
    public String getEvaluationPoints() throws SQLException {
        LOGGER.info("Try to get all evaluation points");
        try {
            return toJson(getEvaluationPointCRUD().getEvaluationPoints());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
    }
}
