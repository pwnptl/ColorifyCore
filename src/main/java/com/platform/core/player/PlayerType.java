package com.platform.core.player;

import lombok.Getter;

@Getter
public enum PlayerType {
    HUMAN("human"),
    BOT("bot"),
    UNRECOGNISED("unrecognised");

    PlayerType(String human) {
    }
}
