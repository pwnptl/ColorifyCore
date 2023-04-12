package com.platform.core.network;

import com.colorify.game.response.Response;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

public class WebSocketHandlerHelper {

    private final SessionsManager sessionsManager;

    public WebSocketHandlerHelper() {
        this.sessionsManager = SessionsManager.getInstance();
    }

    public void broadcastMessageByPlayerIds(Set<String> playerIds, String message) {
        for (String playerId : playerIds) {
            sendMessageByPlayerId(playerId, message);
        }
    }

    public void broadcastMessageByPlayerIds(Set<String> playerIds, MessageHandlerType messageHandlerType, Response response) {
        for (String playerId : playerIds) {
            sendMessageByPlayerId(playerId, messageHandlerType, response);
        }
    }

    public void sendMessageByPlayerId(String playerId, MessageHandlerType messageHandlerType, Response response) {
        try {
            String payload = new Payload(messageHandlerType.getValue(), response).asJson();
            sessionsManager.findSessionByPlayerId(playerId).sendMessage(new TextMessage(payload));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessageByPlayerId(String playerId, String message) {
        try {
            WebSocketSession session = sessionsManager.findSessionByPlayerId(playerId);
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            Logger.error("exception caught in broadcasting to player " + playerId);
            Logger.error(e.getMessage());
            // throw new RuntimeException(e); // todo: broadcast using queues and threads with retryables.
        }
    }

    public void sendMessageBySessionId(String sessionId, MessageHandlerType playerCreated, Response createPlayerResponse) {

    }
}
