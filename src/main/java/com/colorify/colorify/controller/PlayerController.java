package com.colorify.colorify.controller;

import com.colorify.game.PlayerFacade;
import com.platform.core.utility.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    @Autowired
    PlayerFacade playerFacade; // todp: take via @autowired.

    @GetMapping("/createPlayer")
    public String createPlayer(@RequestParam(value = "name", defaultValue = "World") String name) {
        Logger.info("\ncreatePlayer api called");
        return playerFacade.createPlayer(name);
    }
}
