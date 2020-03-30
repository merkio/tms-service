package com.company.tms.commons.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * General purpose com.auto1.shift.commons.exception for all authentication issues.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1181286137089807103L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }
}
