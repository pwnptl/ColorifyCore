package com.colorify.colorify.controller.errors.base;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Collections;

public abstract class BaseError {

    public String error(HttpServletRequest request){
        return "Base Data";
    };
}
