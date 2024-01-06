package com.colorify.colorify.controller;

import com.colorify.colorify.model.responseBuilder.GameDataResponse;
import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public String getJson(@PathVariable(value = "gameId") String gameId,
                          @RequestParam(name = "requirefull", defaultValue = "0") String requireFull) {

        Logger.info(GameController.class.getName(), "init api called");
        String json = ObjectJsonConverter.toJSON(new GameDataResponse(gameFacade.getGame(gameId), ""));
        json = removeUnnecessaryFields(requireFull, json);
        return json;
    }

    private static String removeUnnecessaryFields(String requireFull, String json) {
        if (requireFull.equals("0")) {
            json = ObjectJsonConverter.removeKey(json, "board");
            json = ObjectJsonConverter.removeKey(json, "palette");
            json = ObjectJsonConverter.removeKey(json, "scoreTracker");
        }
        return json;
    }

    @GetMapping("{gameId}/joinGame/{playerId}")
    public String joinGame(@PathVariable(value = "gameId") String gameId,
                           @PathVariable(value = "playerId", required = true) String playerId,
                           @RequestParam(name = "requirefull", defaultValue = "0") String requireFull) {
        String json =  ObjectJsonConverter.toJSON(gameFacade.addPlayer(gameId, playerId));
        json = removeUnnecessaryFields(requireFull, json);
        return json;
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
