package io.space.geek.tms.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceAlreadyExistException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exist with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public static Supplier<ResourceAlreadyExistException> newResourceAlreadyExistException(String resourceName, String fieldName, Object fieldValue) {
        return () -> new ResourceAlreadyExistException(resourceName, fieldName, fieldValue);
    }
}
