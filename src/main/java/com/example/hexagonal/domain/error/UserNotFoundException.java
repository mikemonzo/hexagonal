package com.example.hexagonal.domain.error;

import com.example.hexagonal.domain.UserId;

public class UserNotFoundException extends DomainEntityNotFoundException {

    public UserNotFoundException() {
        super("user");
    }

    public UserNotFoundException(UserId entityId) {
        super("user", entityId.getValue().toString());
    }

}
