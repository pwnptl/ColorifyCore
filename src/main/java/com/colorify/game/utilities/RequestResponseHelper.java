package com.colorify.game.utilities;

import com.colorify.game.request.CreatePlayerRequest;
import com.colorify.game.request.GetPlayerRequest;
import com.google.gson.Gson;

public class RequestResponseHelper {
    final static Gson gson = new Gson();


    public static GetPlayerRequest getPlayerIdRequest(String message) {
        return gson.fromJson(message, GetPlayerRequest.class);
    }

    public static CreatePlayerRequest createPlayerIdRequest(String message) {
        return gson.fromJson(message, CreatePlayerRequest.class);
    }

    public static <T> Object fromJson(String json, Class<T> c) {
        return gson.fromJson(json, c);
    }
}
