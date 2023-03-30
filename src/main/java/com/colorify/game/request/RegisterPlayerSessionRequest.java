package com.colorify.game.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterPlayerSessionRequest {
    private final String userId;
}
