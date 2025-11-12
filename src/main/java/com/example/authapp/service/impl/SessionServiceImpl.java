package com.example.authapp.service.impl;

import com.example.authapp.dto.SessionDTO;
import com.example.authapp.entity.Session;
import com.example.authapp.entity.User;
import com.example.authapp.exception.ResourceNotFoundException;
import com.example.authapp.repository.SessionRepository;
import com.example.authapp.repository.UserRepository;
import com.example.authapp.service.SessionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public SessionDTO createSession(SessionDTO sessionDTO) {
        User user = userRepository.findById(sessionDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", sessionDTO.getUserId()));

        Session session = new Session();
        session.setUser(user);
        session.setIpAddress(sessionDTO.getIpAddress());
        session.setDeviceInfo(sessionDTO.getDeviceInfo());
        
        Session savedSession = sessionRepository.save(session);
        return convertToDTO(savedSession);
    }

    @Override
    @Transactional
    public void endSession(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "id", id));
        
        if (session.getLogoutTime() == null) {
            session.setLogoutTime(LocalDateTime.now());
            sessionRepository.save(session);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SessionDTO> getActiveSessions(Long userId) {
        return sessionRepository.findByUserIdAndLogoutTimeIsNull(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SessionDTO getSessionById(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "id", id));
        return convertToDTO(session);
    }

    private SessionDTO convertToDTO(Session session) {
        SessionDTO dto = modelMapper.map(session, SessionDTO.class);
        dto.setUserEmail(session.getUser().getEmail());
        return dto;
    }
}
