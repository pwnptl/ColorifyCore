package com.platform.core.network;

import com.platform.core.utility.Logger;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Set;

public class SessionsManager {
    private static SessionsManager instance;
    private final HashMap<String, WebSocketSession> sessions;

    // todo : below definitely can be optimised.
    private final HashMap<String, String> playerIdToSessionId;
    private final HashMap<String, String> sessionIdToPlayerId;

    private SessionsManager() {
        sessions = new HashMap<>();
        playerIdToSessionId = new HashMap<>();
        sessionIdToPlayerId = new HashMap<>();
    }

    public static SessionsManager getInstance() {
        if (instance == null)
            instance = new SessionsManager();
        return instance;
    }

    public void add(final WebSocketSession session) {
        sessions.put(session.getId(), session);
    }

    public void addPlayerSession(String playerId, String sessionId) {
        playerIdToSessionId.put(playerId, sessionId);
        sessionIdToPlayerId.put(sessionId, playerId);
    }

    public void removePlayerSession(String playerId) {
        String sessionId = findSessionIdByPlayerId(playerId);
        if (sessionId != null) {
            playerIdToSessionId.remove(playerId);
            sessionIdToPlayerId.remove(sessionId);
        }
    }

    public String findPlayerIdBySessionId(String sessionId) {
        if (sessionIdToPlayerId.containsKey(sessionId))
            return sessionIdToPlayerId.get(sessionId);
        Logger.info(this.getClass().getName(), "player not found for sessionId : " + sessionId);
        return null;
    }

    public String findSessionIdByPlayerId(String playerId) {
        if (playerIdToSessionId.containsKey(playerId))
            return playerIdToSessionId.get(playerId);
        Logger.info(this.getClass().getName(), "session not found for playerId : " + playerId);
        return null;
    }

    public WebSocketSession findSessionByPlayerId(String playerId) {
        String sessionId = findSessionIdByPlayerId(playerId);
        if(sessionId == null) return null;
        return get(sessionId);
    }

    public Set<String> getSessionIds() {
        return sessions.keySet();
    }

    public HashMap<String, String> getPlayerIdsToSessionIds()
    {
        return playerIdToSessionId;
    }

    public String removeSession(final WebSocketSession session) {
        // todo : incomplete impl.
        String playerId = sessionIdToPlayerId.get(session.getId());
        sessions.remove(session.getId());
        return playerId;
    }

    public WebSocketSession get(String sessionId) {
        if (sessions.containsKey(sessionId))
            return sessions.get(sessionId);
        Logger.info(this.getClass().getName(), "session not found : " + sessionId);
        return null;
    }
}
