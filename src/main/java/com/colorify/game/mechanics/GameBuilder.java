package com.colorify.game.mechanics;

import com.colorify.game.mechanics.board.Board;
import com.colorify.game.mechanics.board.BoardBuilder;
import com.colorify.game.mechanics.palette.ColorifyPalette;
import com.colorify.game.mechanics.palette.PaletteBuilder;
import com.colorify.game.utilities.GameConfiguration;
import com.platform.core.game.AbstractBaseGame;
import com.platform.core.game.Palette;


/*
todo: pending refactoring, move methods from BaseGame to this Builder.
 */
public class GameBuilder {
    private Board board;
    private GameConfiguration gameConfiguration;
    private Palette palette;
    public void reset() {

    }

    public GameBuilder addDefaultBoard() {
        gameConfiguration = new GameConfiguration();
        board = new BoardBuilder().withConfiguration(gameConfiguration).withDefaultBoard().build();
        palette = new PaletteBuilder().withConfiguration(gameConfiguration).build(board);
        return this;
    }

    public AbstractBaseGame build() {
        BaseGame baseGame = new BaseGame();
        baseGame.setBoard(board);
        baseGame.setPalette((ColorifyPalette) palette);
        return baseGame;
    }

}
