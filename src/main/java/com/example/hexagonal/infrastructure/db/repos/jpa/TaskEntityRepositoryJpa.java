package com.example.hexagonal.infrastructure.db.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hexagonal.infrastructure.db.entity.TaskEntity;

public interface TaskEntityRepositoryJpa extends JpaRepository<TaskEntity, Long> {
    // Custom query methods can be defined here if needed

}
