package com.platform.core.network;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyWebSocketHandler extends TextWebSocketHandler implements BaseNetwork {
    ArrayList<WebSocketSession> sessions;

    public MyWebSocketHandler() {
        sessions = new ArrayList<>();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("handleTextMessage" + message.toString());
        TextMessage textMessage = new TextMessage("Received " + message.getPayload());
        session.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Opening Session " + session.getId());
        System.out.println("Current Sessions " + sessions.stream().map(WebSocketSession::getId).toList());
        TextMessage textMessage = new TextMessage("Established");
        session.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Closing Session " + session.getId());
    }

    @Override
    public void boradcast(List<String> connectedSessionsIds, String jsonData) {

    }
}
