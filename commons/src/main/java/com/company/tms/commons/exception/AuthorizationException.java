package com.company.tms.commons.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * General purpose com.auto1.shift.commons.exception for all authorization issues.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = -9126984681380898654L;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }

}
