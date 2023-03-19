package com.platform.core.network;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;

public class SessionsManager {
    private static SessionsManager instance;
    private final ArrayList<WebSocketSession> sessions;

    private SessionsManager()
    {
        sessions = new ArrayList<>();
    }

    public static SessionsManager getInstance(){
        if(instance == null)
            instance = new SessionsManager();
        return instance;
    }

    public void add(final WebSocketSession session) {
        sessions.add(session);
    }

    public ArrayList<WebSocketSession> get() {
        return sessions;
    }

    public void remove(final WebSocketSession session) {
        sessions.remove(session);
    }
}
