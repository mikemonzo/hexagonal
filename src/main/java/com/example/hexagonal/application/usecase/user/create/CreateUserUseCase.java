package com.example.hexagonal.application.usecase.user.create;

import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public User create(CreateUserCommand command) {
        User user = User.builder().name(command.name()).email(command.email())
                .password(command.password()).build();
        return userRepository.create(user);
    }
}
