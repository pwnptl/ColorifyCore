package com.colorify.colorify.controller;

import com.platform.core.database.DatabaseAdapter;
import com.platform.core.utility.Logger;
import com.platform.core.utility.ObjectJsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DebuggingController {
    DatabaseAdapter database = new DatabaseAdapter();

    @GetMapping("clearDB")
    public String cleanDB() {
        log.info(GameController.class.getName(), "clear DB called");
        database.clearAll();
        return "success";
    }
    @GetMapping("getAll")
    public String getAll() {
        return database.getAll();

    }
}
