package com.colorify.colorify.controller;

import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/game/")
public class GameController {

    @Autowired
    GameFacade gameFacade; // todp: take via @autowired.

    @GetMapping("/init")
    public String init(@RequestParam(value = "myName", defaultValue = "World") String name) {
        Logger.info("\ninit api called");
        return gameFacade.initGame();
    }
}
