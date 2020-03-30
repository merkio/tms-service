package com.company.tms.auth.exception;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ClientRegistrationException extends org.springframework.security.oauth2.provider.ClientRegistrationException {

    public ClientRegistrationException(String message) {
        super(message);
    }

    public ClientRegistrationException(String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }
}
