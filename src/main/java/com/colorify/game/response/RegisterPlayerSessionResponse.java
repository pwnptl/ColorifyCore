package com.colorify.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterPlayerSessionResponse extends Response {
    final boolean registered;
    final String sessionId;
}
