package io.space.geek.tms.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectEmailAddressException extends RuntimeException {

    public IncorrectEmailAddressException(){
        super("Incorrect email address.");
    }
}
