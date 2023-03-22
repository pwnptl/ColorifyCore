package com.colorify.game.utilities;

import com.colorify.game.request.GetPlayer;
import com.google.gson.Gson;

public class RequestResponseHelper {
    final static Gson gson = new Gson();
    public static String getPlayerIdFromMessage(String message) {
        //todo getPlayerId from json message;

        Payload payload = gson.fromJson(message, Payload.class);

        String messageData = gson.fromJson(payload.getMessageData(), String.class);


        return "";


    }


    public static GetPlayer getPlayerIdRequest(String message) {
        return gson.fromJson(message, GetPlayer.class);
    }
}
