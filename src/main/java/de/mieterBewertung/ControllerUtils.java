package de.mieterBewertung;

import com.google.gson.Gson;

public final class ControllerUtils {

    public static String toJson(Object value) {
        return new Gson().toJson(value);
    }
}
