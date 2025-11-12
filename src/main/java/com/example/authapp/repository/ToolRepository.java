package com.example.authapp.repository;

import com.example.authapp.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    Optional<Tool> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
