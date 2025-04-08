package com.example.hexagonal.application.usecase.user.create;

public record CreateUserCommand(String name, String email, String password) {

}
