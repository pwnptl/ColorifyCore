package com.colorify.colorify;

import com.colorify.game.AbstractBaseGame;
import com.colorify.game.GameStateMachineInitializer;
import com.colorify.game.mechanics.BaseGame;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.platform.core.errors.IllegalStateError;
import com.platform.core.errors.NotImplementedError;
import com.platform.core.utility.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@RestController
public class ColorifyApplication {

    private static Logger logger = new Logger();

    public static void main(String[] args) {
        SpringApplication.run(ColorifyApplication.class, args);
    }


    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/init")
    public String init(@RequestParam(value = "myName", defaultValue = "World") String name) throws IllegalStateError, NotImplementedError, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        logger.info("\ninit called");
        AbstractBaseGame baseGame = new BaseGame();
        GameStateMachineInitializer gameStateMachineInitializer = new GameStateMachineInitializer();
        gameStateMachineInitializer.init(baseGame);
        gameStateMachineInitializer.startMachine();
        return gameData(baseGame);
    }

    private String gameData(AbstractBaseGame baseGame) {
        return baseGame.getData();

    }
}
