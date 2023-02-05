package com.colorify.colorify;

import com.colorify.game.GameFacade;
import com.platform.core.utility.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ColorifyApplication {

    GameFacade gameFacade = new GameFacade(); // todp: take via @autowired.
    private final Logger logger = new Logger();

    public static void main(String[] args) {
        SpringApplication.run(ColorifyApplication.class, args);
    }


    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/init")
    public String init(@RequestParam(value = "myName", defaultValue = "World") String name) {
        logger.info("\ninit api called");
        return gameFacade.initGame();
    }
    @GetMapping("/createPlayer")
    public String createPlayer(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("\ncreatePlayer api called");
        return gameFacade.createPlayer(name);
    }
}
