package com.colorify.colorify.controller.errors;

import com.colorify.colorify.controller.errors.base.BaseError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class Error404 extends BaseError {
    private final String ERROR_PATH = "/404";

    @Override
    @RequestMapping(value = ERROR_PATH)
    public String error(HttpServletRequest request) {
        super.error(request);
        return "404";
    }
}
