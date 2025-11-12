package com.example.authapp.controller;

import com.example.authapp.dto.UserActivityDTO;
import com.example.authapp.service.UserActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @PostMapping
    public ResponseEntity<UserActivityDTO> createActivity(@RequestBody UserActivityDTO activityDTO) {
        UserActivityDTO createdActivity = userActivityService.createActivity(activityDTO);
        return ResponseEntity.ok(createdActivity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserActivityDTO> getActivityById(@PathVariable Long id) {
        UserActivityDTO activity = userActivityService.getActivityById(id);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<UserActivityDTO>> getActivitiesByUser(
            @PathVariable Long userId,
            Pageable pageable) {
        Page<UserActivityDTO> activities = userActivityService.getActivitiesByUserId(userId, pageable);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/tool/{toolId}")
    public ResponseEntity<Page<UserActivityDTO>> getActivitiesByTool(
            @PathVariable Long toolId,
            Pageable pageable) {
        Page<UserActivityDTO> activities = userActivityService.getActivitiesByToolId(toolId, pageable);
        return ResponseEntity.ok(activities);
    }

    @GetMapping
    public ResponseEntity<Page<UserActivityDTO>> getAllActivities(Pageable pageable) {
        Page<UserActivityDTO> activities = userActivityService.getAllActivities(pageable);
        return ResponseEntity.ok(activities);
    }
}
