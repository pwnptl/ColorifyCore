package com.colorify.colorify;

import com.colorify.colorify.controller.PlayerController;
import com.platform.core.registry.messageHandler.MessageHandlerRegistry;
import com.platform.core.registry.messageHandler.MessageHandlerType;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ColorifyApplication {
    private static MessageHandlerRegistry messageHandlerRegistry;

    public static void main(String[] args) {
        initHandlers();
        SpringApplication.run(ColorifyApplication.class, args);
    }

    private static void initHandlers() {
        // todo : move this init() to post startup in spring usign conventional method.
        messageHandlerRegistry = MessageHandlerRegistry.getInstance();
        messageHandlerRegistry.put(MessageHandlerType.PLAYER_DATA, new PlayerController().getGetPlayerRequestHandler());
    }

    @RequestMapping(value = "/hello",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = "application/json")
    public String sayHello() {
        return ObjectJsonConverter.toJSON("hello");
    }
}
