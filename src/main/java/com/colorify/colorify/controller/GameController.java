package com.colorify.colorify.controller;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/")
public class GameController extends BaseController {

    @Autowired
    GameFacade gameFacade;

    @GetMapping("init")
    public String init() {
        Logger.info(GameController.class.getName(), "init api called");
        return ObjectJsonConverter.toJSON(gameFacade.initGame());
    }

    @GetMapping("{gameId}/get")
    public String getHtml(@PathVariable(value = "gameId") String gameId) {
        Logger.info(GameController.class.getName(), "init api called");
        return gameFacade.getSerializedGame(gameId).replace("\n", "<br/>");
    }

    @GetMapping(value = "{gameId}/getjson", produces = MediaType.TEXT_HTML_VALUE)
    public String getJson(@PathVariable(value = "gameId") String gameId) {
        Logger.info(GameController.class.getName(), "init api called");
        return ObjectJsonConverter.toJSON(new GameDataResponse(gameFacade.getGame(gameId), ""));
    }

    @GetMapping("{gameId}/joinGame/{playerId}")
    public String joinGame(@PathVariable(value = "gameId") String gameId, @PathVariable(value = "playerId", required = true) String playerId) {
        return ObjectJsonConverter.toJSON(gameFacade.addPlayer(gameId, playerId));
    }

    @GetMapping("{gameId}/start")
    public String startGame(@PathVariable(value = "gameId") String gameId) {
        return ObjectJsonConverter.toJSON(gameFacade.startGame(gameId));
    }

    @GetMapping("{gameId}/makeMove/{playerId}/move/{chosenCell}")
    public String makeMove(@PathVariable(value = "gameId", required = true) String gameId,
                           @PathVariable(value = "chosenCell", required = true) int chosenCell,
                           @PathVariable(value = "playerId", required = true) String playerId) {
        return ObjectJsonConverter.toJSON(gameFacade.makeMove(gameId, playerId, chosenCell));
    }
}
