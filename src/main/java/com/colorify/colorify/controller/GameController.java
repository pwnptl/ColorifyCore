package com.colorify.colorify.controller;

import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
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
        return gameFacade.initGame();
    }
    @GetMapping("{gameId}/addPlayer")
    public String addPlayer(@PathVariable(value = "gameId") String gameId, @RequestParam(value = "playerId", required = false) String playerId) {
        return gameFacade.addPlayer(gameId, playerId);
    }
}
