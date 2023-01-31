package com.colorify.colorify.controller.errors.base;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

public abstract class BaseError {

    public  List<String> error(HttpServletRequest request){
        return Collections.singletonList("Base Data");
    };
}
