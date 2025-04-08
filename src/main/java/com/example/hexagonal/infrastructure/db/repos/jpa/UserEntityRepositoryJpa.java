package com.example.hexagonal.infrastructure.db.repos.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hexagonal.infrastructure.db.entity.UserEntity;

public interface UserEntityRepositoryJpa extends JpaRepository<UserEntity, UUID> {

}
