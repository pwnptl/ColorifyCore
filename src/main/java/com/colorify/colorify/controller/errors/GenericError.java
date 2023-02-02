package com.colorify.colorify.controller.errors;

import com.colorify.colorify.controller.errors.base.BaseError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenericError extends BaseError {

    private static final String ERROR_PATH = "/errors";

    @Override
    @RequestMapping(value = ERROR_PATH)
    public String error(HttpServletRequest request) {
        super.error(request);
        return "sessionId " + request.getRequestedSessionId();
    }
}
