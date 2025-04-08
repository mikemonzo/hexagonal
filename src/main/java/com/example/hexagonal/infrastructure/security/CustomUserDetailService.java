package com.example.hexagonal.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.hexagonal.domain.repository.UserRepository;
import com.example.hexagonal.infrastructure.security.model.AuthUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByEmail(username).map(AuthUser::of)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email %s".formatted(username)));
    }

}
