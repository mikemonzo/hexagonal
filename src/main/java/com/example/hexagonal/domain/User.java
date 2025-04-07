package com.example.hexagonal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
public class User {

    @Setter(AccessLevel.NONE)
    private UserId id;

    private String name;
    private String email;
    private String password;

    @Builder.Default
    private String role = "USER";
}
