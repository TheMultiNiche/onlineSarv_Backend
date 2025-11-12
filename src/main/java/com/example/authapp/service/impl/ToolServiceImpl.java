package com.example.authapp.service.impl;

import com.example.authapp.dto.ToolDTO;
import com.example.authapp.entity.Tool;
import com.example.authapp.exception.ResourceNotFoundException;
import com.example.authapp.repository.ToolRepository;
import com.example.authapp.service.ToolService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;
    private final ModelMapper modelMapper;

    public ToolServiceImpl(ToolRepository toolRepository, ModelMapper modelMapper) {
        this.toolRepository = toolRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ToolDTO createTool(ToolDTO toolDTO) {
        Tool tool = modelMapper.map(toolDTO, Tool.class);
        Tool savedTool = toolRepository.save(tool);
        return modelMapper.map(savedTool, ToolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ToolDTO getToolById(Long id) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + id));
        return modelMapper.map(tool, ToolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public ToolDTO getToolBySlug(String slug) {
        Tool tool = toolRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with slug: " + slug));
        return modelMapper.map(tool, ToolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ToolDTO> getAllTools(Pageable pageable) {
        return toolRepository.findAll(pageable)
                .map(tool -> modelMapper.map(tool, ToolDTO.class));
    }

    @Override
    @Transactional
    public ToolDTO updateTool(Long id, ToolDTO toolDTO) {
        Tool existingTool = toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with id: " + id));
        
        modelMapper.map(toolDTO, existingTool);
        existingTool.setId(id); // Ensure ID is not changed
        Tool updatedTool = toolRepository.save(existingTool);
        return modelMapper.map(updatedTool, ToolDTO.class);
    }

    @Override
    @Transactional
    public void deleteTool(Long id) {
        if (!toolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tool not found with id: " + id);
        }
        toolRepository.deleteById(id);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return toolRepository.existsBySlug(slug);
    }
}
