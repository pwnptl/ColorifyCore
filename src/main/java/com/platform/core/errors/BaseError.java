package com.platform.core.errors;

public class BaseError extends Exception {
    BaseError(String errorlog){
        super(errorlog);
    }
}
