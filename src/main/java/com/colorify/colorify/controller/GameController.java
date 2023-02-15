package com.colorify.colorify.controller;

import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game/")
public class GameController {

    @Autowired
    GameFacade gameFacade;

    @GetMapping("/init")
    public String init(@RequestParam(value = "myName", defaultValue = "World") String name) {
        Logger.info("\ninit api called");
        return ObjectJsonConverter.toJSON(gameFacade.initGame());
    }

    @GetMapping("{gameId}/addPlayer")
    public String addPlayer(@PathVariable(value = "gameId") String gameId, @RequestParam(value = "playerId", required = false) String playerId) {
        return ObjectJsonConverter.toJSON(gameFacade.addPlayer(gameId, playerId));
    }

    @GetMapping("{gameId}/start")
    public String startGame(@PathVariable(value = "gameId") String gameId) {
        return ObjectJsonConverter.toJSON(gameFacade.startGame(gameId));
    }

    @GetMapping("{gameId}/makeMove/{playerId}/move/{moveNumber}")
    public String makeMove(@PathVariable(value = "gameId", required = true) String gameId,
                           @PathVariable(value = "moveNumber", required = true) String moveNo,
                           @PathVariable(value = "playerId", required = true) String playerId) {
        return ObjectJsonConverter.toJSON(gameFacade.moveNumber(gameId, playerId, moveNo));
    }


}
