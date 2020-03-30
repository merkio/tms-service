package com.company.tms.auth.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorizationCodeNotFoundException extends RuntimeException {

    public AuthorizationCodeNotFoundException(String message) {
        super(message);
    }

    public AuthorizationCodeNotFoundException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }

}
