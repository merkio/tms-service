package com.company.tms.auth.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchClientException extends org.springframework.security.oauth2.provider.NoSuchClientException {

    public NoSuchClientException(String message) {
        super(message);
    }

    public NoSuchClientException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }
}
