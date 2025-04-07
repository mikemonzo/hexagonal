package com.example.hexagonal.domain;

import java.util.UUID;
import lombok.Value;

@Value
public class UserId {
    UUID value;

    public static UserId of(UUID id) {
        return new UserId(id);
    }
}
