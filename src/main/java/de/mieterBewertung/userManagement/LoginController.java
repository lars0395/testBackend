package de.mieterBewertung.userManagement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static de.mieterBewertung.ControllerUtils.toJson;
import de.mieterBewertung.database.exception.UnkownException;
import de.mieterBewertung.database.exception.UnkownPassword;
import de.mieterBewertung.database.exception.UnkownUser;
import de.mieterBewertung.database.exception.UserAlreadyExisting;
import de.mieterBewertung.userManagement.exception.AlreadyLoggedInException;
import de.mieterBewertung.userManagement.exception.InvalidSessionException;
import de.mieterBewertung.userManagement.exception.NoSessionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
    private UserCRUD userCRUD;

    private UserCRUD getUserCRUD() {
        if(userCRUD == null) {
            userCRUD = new UserCRUD();
        }
        return userCRUD;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestBody String body) throws UnkownPassword, UnkownException, AlreadyLoggedInException, UnkownUser {
        LOGGER.info("Try to login\n{}", body);
        User user = new Gson().fromJson(body, User.class);
        LOGGER.info(body);
        try {
            getUserCRUD().loginUser(user);
            UserSessionMapping.getUserSessionMapping().createSession(user);
            return new Gson().toJson(user);
        } catch (UnkownUser | UnkownPassword | UnkownException | AlreadyLoggedInException e) {
            LOGGER.error(e);
            throw e;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String body) throws InvalidSessionException, NoSessionException {
        LOGGER.info("Try to logout\n{}", body);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        String user = jsonObject.get("user").getAsString();
        String sessionId = jsonObject.get("sessionid").getAsString();
        try {
            UserSessionMapping.getUserSessionMapping().deleteSession(user, sessionId);
            return toJson("SUCCESS");
        } catch (InvalidSessionException | NoSessionException e) {
            LOGGER.error(e);
            throw e;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/createUser", method = RequestMethod.POST)
    public String createUser(@RequestBody String body) throws UserAlreadyExisting, UnkownException {
        LOGGER.info("Try to create\n{}", body);
        User user = new Gson().fromJson(body, User.class);
        try {
            getUserCRUD().createUser(user);
            return toJson("Successfully created user");
        } catch (UnkownException | UserAlreadyExisting e) {
            LOGGER.error(e);
            throw e;
        }
    }
}
