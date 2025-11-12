package com.example.authapp.service;

import com.example.authapp.dto.UserActivityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserActivityService {
    UserActivityDTO createActivity(UserActivityDTO activityDTO);
    UserActivityDTO getActivityById(Long id);
    Page<UserActivityDTO> getActivitiesByUserId(Long userId, Pageable pageable);
    Page<UserActivityDTO> getActivitiesByToolId(Long toolId, Pageable pageable);
    Page<UserActivityDTO> getAllActivities(Pageable pageable);
}
