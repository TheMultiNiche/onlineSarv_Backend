package com.example.authapp.service;

import com.example.authapp.dto.SessionDTO;

import java.util.List;

public interface SessionService {
    SessionDTO createSession(SessionDTO sessionDTO);
    void endSession(Long id);
    List<SessionDTO> getActiveSessions(Long userId);
    SessionDTO getSessionById(Long id);
}
