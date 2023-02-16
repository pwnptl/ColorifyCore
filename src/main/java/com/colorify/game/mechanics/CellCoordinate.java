package com.colorify.game.mechanics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellCoordinate {
    private String playerId;
    private int r, c;
}
