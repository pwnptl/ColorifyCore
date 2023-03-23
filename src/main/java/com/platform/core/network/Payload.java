package com.platform.core.network;

import com.google.gson.Gson;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Payload {
    private final String messageType;
    private final String messageData;
    private static final Gson gson = new Gson();

    public Payload(String messageType, Object obj) {
        String messageData = ObjectJsonConverter.toJSON(obj);
        this.messageType = messageType;
        this.messageData = messageData;
    }

    public static Payload fromJson(String json) {
        Payload payload = gson.fromJson(json, Payload.class);
//        String s = gson.fromJson(payload.messageData, String.class);
//        if(ObjectJsonConverter.isJson(s))
//            payload.messageData = s;
        return payload;
    }

    public String asJson() {
        return ObjectJsonConverter.toJSON(this);
    }
}