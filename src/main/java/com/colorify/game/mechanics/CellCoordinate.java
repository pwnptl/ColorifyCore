package com.colorify.game.mechanics;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CellCoordinate implements Serializable {
    private String playerId;
    private int r, c;
}
