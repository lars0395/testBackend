package de.mieterBewertung.userManagement;

import de.mieterBewertung.userManagement.exception.AlreadyLoggedInException;
import de.mieterBewertung.userManagement.exception.InvalidSessionException;
import de.mieterBewertung.userManagement.exception.NoSessionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserSessionMapping {

    private static final Logger LOGGER = LogManager.getLogger(UserSessionMapping.class);
    private static UserSessionMapping instance = null;
    private Map<String, User> map;

    public static UserSessionMapping getUserSessionMapping() {
        if (instance == null) {
            instance = new UserSessionMapping();
        }
        return instance;
    }

    private UserSessionMapping() {
        map = new HashMap<>();
    }

    public boolean validateSession(String username, String session) throws InvalidSessionException, NoSessionException {
        LOGGER.info("Start validating session");
        User user = map.get(username);
        if (user == null) {
            throw new NoSessionException("User has no active session");
        }
        if (user.getSession().equals(session)) {
            LOGGER.info("Session is valid");
            return true;
        }
        LOGGER.info("Session is invalid");
        throw new InvalidSessionException("Session is invalid");
    }

    public String createSession(User user) throws AlreadyLoggedInException {
        if (map.containsKey(user.getUsername())) {
            throw new AlreadyLoggedInException("User has already an active session");
        }
        String sessionId = UUID.randomUUID().toString();
        user.setSession(sessionId);
        map.put(user.getUsername(), user);
        LOGGER.info("Created session '{}' for user '{}'", sessionId, user.getUsername());
        return sessionId;
    }

    public void deleteSession(String username, String session) throws InvalidSessionException, NoSessionException {
        if (validateSession(username, session)) {
            map.remove(username);
        }
    }
}
