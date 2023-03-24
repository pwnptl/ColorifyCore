package com.colorify.colorify;

import com.platform.core.utility.ObjectJsonConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ColorifyApplication {


    public static void main(String[] args) {
        SpringApplication.run(ColorifyApplication.class, args);
    }

    @RequestMapping(value = "/hello",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = "application/json")
    public String sayHello() {
        return ObjectJsonConverter.toJSON("hello");
    }
}
