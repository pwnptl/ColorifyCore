package com.platform.core.registry.messageHandler;

import lombok.Getter;

public enum MessageHandlerType {

    // Requests -> should not require handler if explicit Response type is present. todo : simplify this incompetence.
    GET_PLAYER_DATA("GET_PLAYER_DATA"),
    SYN("SYN"),

    // Responses
    PLAYER_DATA("PLAYER_DATA"),
    ACK("ACK"),


    // Misc
    START_BUTTON_MESSAGE_HANDLER("    START_BUTTON_MESSAGE_HANDLER(\"DATA_HANDLER\"),\n"),
    UNKNOWN("UNKNOWN"),
    DEFAULT("DEFAULT")
    ;

    /*
    * Value of the enum.
    * */
    @Getter
    private final String value;

    MessageHandlerType(String value) {

        this.value = value;
    }

    public static MessageHandlerType getValue(String type) {
        for (MessageHandlerType t : values())
            if (t.value.equalsIgnoreCase(type)) return t;
        throw new IllegalArgumentException("unknown enum type : " + type);
    }
}
