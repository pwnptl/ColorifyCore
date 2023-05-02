package com.platform.core.network;

import com.colorify.game.response.Response;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;

public class WebSocketHandlerHelper {

    private final SessionsManager sessionsManager;

    public WebSocketHandlerHelper() {
        this.sessionsManager = SessionsManager.getInstance();
    }


    public void broadcastMessageByPlayerIds(ArrayList<String> playerIds, MessageHandlerType messageHandlerType, Response response) {
        Logger.info("players to Broadcast : " + playerIds);
        for (String playerId : playerIds) {
            sendMessageByPlayerId(playerId, messageHandlerType, response);
        }
    }

    public void sendMessageByPlayerId(String playerId, MessageHandlerType messageHandlerType, Response response) {
        try {

            WebSocketSession session = sessionsManager.findSessionByPlayerId(playerId);
            if (session == null)
                Logger.error("session Id not found for playerId " + playerId);
            else
                session.sendMessage(getPayloadTextMessage(messageHandlerType, response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageBySessionId(String sessionId, MessageHandlerType messageHandlerType, Response response) {
        try {
            sessionsManager.get(sessionId).sendMessage(getPayloadTextMessage(messageHandlerType, response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TextMessage getPayloadTextMessage(MessageHandlerType messageHandlerType, Response response) {
        String payload = new Payload(messageHandlerType.getValue(), response).asJson();
        return new TextMessage(payload);
    }
}
