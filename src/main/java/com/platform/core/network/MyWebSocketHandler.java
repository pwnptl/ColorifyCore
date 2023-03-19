package com.platform.core.network;

import com.platform.core.registry.messageHandler.MessageHandlerRegistry;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MyWebSocketHandler extends TextWebSocketHandler implements BaseNetwork {

    private final SessionsManager sessionsManager;
    private final MessageHandlerRegistry messageHandlerRegistry;

    public MyWebSocketHandler() {
        sessionsManager = SessionsManager.getInstance();
        messageHandlerRegistry = MessageHandlerRegistry.getInstance();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage tMessage) throws IOException {
        String message = tMessage.getPayload();
        if (ObjectJsonConverter.isJson(message)) {
            MessageHandlerType type = ObjectJsonConverter.getMessageType(message);
            Logger.info("WebSocket", "Message received is a json " + message + " and type " + type);
            Objects.requireNonNull(this.messageHandlerRegistry.get(type))
                    .handleMessage(message);
        } else {
            this.messageHandlerRegistry.get(MessageHandlerType.DEFAULT)
                    .handleMessage(message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionsManager.add(session);
        System.out.println("Opening Session " + session.getId());
        System.out.println("Current Sessions " + sessionsManager.get().stream().map(WebSocketSession::getId).toList());
        TextMessage textMessage = new TextMessage(ObjectJsonConverter.toJSONWithType(MessageHandlerType.UNKNOWN.name(), "Established"));
        session.sendMessage(textMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionsManager.remove(session);
        System.out.println("Closing Session " + session.getId());
    }

    @Override
    public void boradcast(List<String> connectedSessionsIds, String jsonData) {

    }
}
