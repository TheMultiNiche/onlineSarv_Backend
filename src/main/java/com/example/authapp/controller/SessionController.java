package com.example.authapp.controller;

import com.example.authapp.dto.SessionDTO;
import com.example.authapp.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@RequestBody SessionDTO sessionDTO) {
        SessionDTO createdSession = sessionService.createSession(sessionDTO);
        return ResponseEntity.ok(createdSession);
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<Void> endSession(@PathVariable Long id) {
        sessionService.endSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<SessionDTO>> getActiveSessions(@PathVariable Long userId) {
        List<SessionDTO> activeSessions = sessionService.getActiveSessions(userId);
        return ResponseEntity.ok(activeSessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable Long id) {
        SessionDTO session = sessionService.getSessionById(id);
        return ResponseEntity.ok(session);
    }
}
