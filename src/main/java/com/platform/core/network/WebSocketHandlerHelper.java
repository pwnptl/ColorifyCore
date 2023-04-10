package com.platform.core.network;

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
            String sessionId = sessionsManager.findSessionIdByPlayerId(playerId);
            WebSocketSession session = sessionsManager.get(sessionId);
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                Logger.error("exception caught in broadcasting to player " + playerId);
                Logger.error(e.getMessage());
                // throw new RuntimeException(e); // todo: broadcast using queues and threads with retryables.
            }
        }
    }
}
