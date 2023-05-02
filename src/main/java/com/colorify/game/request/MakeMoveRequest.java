package com.colorify.game.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MakeMoveRequest extends Request {
    @NonNull
    private final String playerId;
    @NonNull
    private final String gameId;
    private final int chosenColor;
}
