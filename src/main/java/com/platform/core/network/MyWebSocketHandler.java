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
            Payload payload = Payload.fromJson(message);
            MessageHandlerType type = MessageHandlerType.getValue(payload.getMessageType());
            Logger.info(MyWebSocketHandler.class.getName(), "Message received is a data " + payload.getMessageData() + " and type " + payload.getMessageType());
            Objects.requireNonNull(this.messageHandlerRegistry.get(type))
                    .handleMessage(session.getId(), payload.getMessageData());
        } else {
            this.messageHandlerRegistry.get(MessageHandlerType.DEFAULT)
                    .handleMessage(session.getId(), message);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionsManager.add(session);
        Logger.info(MyWebSocketHandler.class.getName(), "Opening Session " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionsManager.remove(session);
        Logger.info(MyWebSocketHandler.class.getName(), "Closing Session " + session.getId());
    }

}
