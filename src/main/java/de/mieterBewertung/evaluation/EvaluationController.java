package de.mieterBewertung.evaluation;

import com.google.gson.Gson;
import static de.mieterBewertung.ControllerUtils.toJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class EvaluationController {

    private static final Logger LOGGER = LogManager.getLogger(EvaluationController.class);
    private EvaluationCRUD evaluationCRUD;

    private EvaluationCRUD getEvaluationCRUD() {
        if(evaluationCRUD == null) {
            this.evaluationCRUD = new EvaluationCRUD();
        }
        return this.evaluationCRUD;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/sendEvaluation", method = RequestMethod.POST)
    public String getEvaluationPoints(@RequestBody String body) throws SQLException {
        LOGGER.info("Try to store the evaluation\n {}", body);
        try {
            getEvaluationCRUD().createEvaluation(new Gson().fromJson(body, Evaluation.class));
            return toJson("SUCCESS");
        } catch (SQLException e) {
            LOGGER.error(e);
            throw e;
        }
    }
}
