package com.platform.core.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * todo: use one json library.
 */
public class ObjectJsonConverter {


    final static String MESSAGE_TYPE = "messageType";
    final static String MESSAGE_DATA = "messageData";
    private static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static String toJSON(Object object) {
        try {
            String json = ow.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toPrettyJSON(Object object) {
        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
        return gsonPretty.toJson(object);
    }

    public static boolean isJson(String message) {
        // https://stackoverflow.com/a/10174938/5800424
        try {
            new JSONObject(message);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(message);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static String removeKey(String json, String key) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        if (jsonObject.has(key)) {
            // Remove the key "age" from the JSON object
            jsonObject.remove(key);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }
}
