package com.platform.core.registry.messageHandler;


import com.platform.core.utility.Logger;

import java.util.HashMap;

public class MessageHandlerRegistry {
    private static MessageHandlerRegistry messageHandlerRegistry;

    private MessageHandlerRegistry() {
        messageHandlers = new HashMap<>();
    }

    private HashMap<MessageHandlerType, MessageHandlerInterface> messageHandlers;

    public static MessageHandlerRegistry getInstance() {
        if (messageHandlerRegistry == null)
            messageHandlerRegistry = new MessageHandlerRegistry();
        return messageHandlerRegistry;
    }

    public void put(MessageHandlerType type, MessageHandlerInterface messageHandler) {
        if (messageHandlers.containsKey(type))
            Logger.info(MessageHandlerRegistry.class.getName(), "messageHandler already exist : " + type);
        else
            messageHandlers.put(type, messageHandler);
    }

    public MessageHandlerInterface get(MessageHandlerType handlerType) {
        if (messageHandlers.containsKey(handlerType))
            return messageHandlers.get(handlerType);
        else
            throw new IllegalArgumentException("No handler Found for type " + handlerType);
    }
}
