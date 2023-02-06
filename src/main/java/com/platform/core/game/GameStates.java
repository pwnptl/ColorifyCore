package com.platform.core.game;

import lombok.Getter;

public enum GameStates {
    INITIALIZED("initialized"),
    WAITING_FOR_PLAYERS("waiting_for_players_to_join"),
    START("start"),
    WAITING_FOR_PLAYER_MOVE("waiting_for_player_move"),
    MAKE_MOVE("make_move"),
    VALIDATE_GAME("validate_game"),
    FINISH("finish"),
    AWARDS("award_player"),
    TERMINATE("terminate");


    @Getter
    private String value;

    GameStates(String value) {
        this.value = value;
    }

}
