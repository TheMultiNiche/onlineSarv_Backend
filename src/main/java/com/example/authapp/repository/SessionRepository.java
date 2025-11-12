package com.example.authapp.repository;

import com.example.authapp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserIdAndLogoutTimeIsNull(Long userId);
    
    @Modifying
    @Query("UPDATE Session s SET s.logoutTime = :logoutTime WHERE s.id = :id")
    void updateLogoutTime(@Param("id") Long id, @Param("logoutTime") LocalDateTime logoutTime);
}
