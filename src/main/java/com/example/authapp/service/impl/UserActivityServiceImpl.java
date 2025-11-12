package com.example.authapp.service.impl;

import com.example.authapp.dto.UserActivityDTO;
import com.example.authapp.entity.Tool;
import com.example.authapp.entity.User;
import com.example.authapp.entity.UserActivity;
import com.example.authapp.exception.ResourceNotFoundException;
import com.example.authapp.repository.ToolRepository;
import com.example.authapp.repository.UserActivityRepository;
import com.example.authapp.repository.UserRepository;
import com.example.authapp.service.UserActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final ToolRepository toolRepository;
    private final ModelMapper modelMapper;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository, UserRepository userRepository, ToolRepository toolRepository, ModelMapper modelMapper) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserActivityDTO createActivity(UserActivityDTO activityDTO) {
        User user = userRepository.findById(activityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", activityDTO.getUserId()));
        
        Tool tool = toolRepository.findById(activityDTO.getToolId())
                .orElseThrow(() -> new ResourceNotFoundException("Tool", "id", activityDTO.getToolId()));

        UserActivity activity = new UserActivity();
        activity.setUser(user);
        activity.setTool(tool);
        activity.setStatus(activityDTO.getStatus());
        activity.setInputType(activityDTO.getInputType());
        activity.setOutputType(activityDTO.getOutputType());
        activity.setMetadata(activityDTO.getMetadata());

        UserActivity savedActivity = userActivityRepository.save(activity);
        return convertToDTO(savedActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserActivityDTO getActivityById(Long id) {
        UserActivity activity = userActivityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
        return convertToDTO(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserActivityDTO> getActivitiesByUserId(Long userId, Pageable pageable) {
        return userActivityRepository.findByUserId(userId, pageable)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserActivityDTO> getActivitiesByToolId(Long toolId, Pageable pageable) {
        return userActivityRepository.findByToolId(toolId, pageable)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserActivityDTO> getAllActivities(Pageable pageable) {
        return userActivityRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    private UserActivityDTO convertToDTO(UserActivity activity) {
        UserActivityDTO dto = modelMapper.map(activity, UserActivityDTO.class);
        dto.setUserId(activity.getUser().getId());
        dto.setToolId(activity.getTool().getId());
        dto.setUserName(activity.getUser().getEmail()); // Assuming email is used as username
        dto.setToolName(activity.getTool().getToolName());
        return dto;
    }
}
