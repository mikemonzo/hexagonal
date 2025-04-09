package com.example.hexagonal.domain.validation;

import com.example.hexagonal.domain.error.DomainValidationException;

public class EmailAlreadyExistsException extends DomainValidationException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException() {
        super("The email must be unique.");
    }

}
