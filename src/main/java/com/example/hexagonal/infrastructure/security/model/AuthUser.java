package com.example.hexagonal.infrastructure.security.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.hexagonal.domain.User;
import com.example.hexagonal.domain.UserId;
import com.example.hexagonal.infrastructure.db.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser implements UserDetails {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UserId getIdAsUserId() {
        return UserId.of(id);
    }

    public static AuthUser of(User user) {
        return AuthUser.builder().id(user.getId().getValue()).name(user.getName())
                .email(user.getEmail()).password(user.getPassword())
                .role(UserRole.of(user.getRole())).build();
    }
}
