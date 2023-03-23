package com.colorify.colorify.controller;

import com.colorify.game.PlayerFacade;
import com.platform.core.network.SessionsManager;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player/")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerFacade playerFacade;

    @Autowired
    private SessionsManager sessionsManager;

    @GetMapping("/create")
    public String createPlayer(@RequestParam(value = "name", defaultValue = "dummy") String name) {
        Logger.info("\ncreatePlayer api called");
        return ObjectJsonConverter.toJSON(playerFacade.createPlayer(name));
    }

    @GetMapping("/get")
    public String getPlayer(@RequestParam(value = "playerId", defaultValue = "dummyId") String id) {
        Logger.info("\ncreatePlayer api called");
        return ObjectJsonConverter.toJSON(playerFacade.getPlayer(id));
    }
 }