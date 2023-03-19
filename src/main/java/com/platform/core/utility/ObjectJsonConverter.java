package com.platform.core.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonObject;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static String toJSONWithType(String messageType, Object obj) {
        String jsonObj = toJSON(obj);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("messageType", messageType);
        jsonObject.addProperty("messageData", toJSON(obj));
        return jsonObj;
    }

    public static String dummyJsonResponse() {
        String data = "data";
        String type = "PLAYER_DATA";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(MESSAGE_DATA, data);
        jsonObject.addProperty(MESSAGE_TYPE, type);
        Logger.info("dummy response " + jsonObject.toString());
        return jsonObject.toString();
    }

    public static MessageHandlerType getMessageType(String message) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(message);
            String type = jsonObject.getString(MESSAGE_TYPE);
            return MessageHandlerType.getValue(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return MessageHandlerType.UNKNOWN;
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
}
