package com.example.hexagonal.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.hexagonal.application.usecase.user.create.CreateUserUseCase;
import com.example.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.example.hexagonal.domain.repository.UserRepository;
import com.example.hexagonal.infrastructure.db.repos.impl.UserRepositoryImpl;
import com.example.hexagonal.infrastructure.db.repos.jpa.UserEntityRepositoryJpa;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserEntityRepositoryJpa userEntityRepositoryJpa;

    @Bean
    public UserRepository userRepositoryJpa() {
        return new UserRepositoryImpl(userEntityRepositoryJpa, passwordEncoder);
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepositoryJpa());
    }

    @Bean
    public FindUserUseCase findUserUseCase() {
        return new FindUserUseCase(userRepositoryJpa());
    }
}
