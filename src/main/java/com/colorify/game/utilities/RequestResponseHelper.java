package com.colorify.game.utilities;

import com.colorify.game.request.GetPlayer;
import com.google.gson.Gson;

public class RequestResponseHelper {
    final static Gson gson = new Gson();


    public static GetPlayer getPlayerIdRequest(String message) {
        return gson.fromJson(message, GetPlayer.class);
    }
}
