package com.platform.core.registry.messageHandler;


import java.io.IOException;

public interface MessageHandlerInterface {
    public void handleMessage(String sessionId, String message) throws IOException;
}