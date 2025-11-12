package com.example.authapp.repository;

import com.example.authapp.entity.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    Page<UserActivity> findByUserId(Long userId, Pageable pageable);
    Page<UserActivity> findByToolId(Long toolId, Pageable pageable);
}
