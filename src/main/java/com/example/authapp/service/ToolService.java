package com.example.authapp.service;

import com.example.authapp.dto.ToolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ToolService {
    ToolDTO createTool(ToolDTO toolDTO);
    ToolDTO getToolById(Long id);
    ToolDTO getToolBySlug(String slug);
    Page<ToolDTO> getAllTools(Pageable pageable);
    ToolDTO updateTool(Long id, ToolDTO toolDTO);
    void deleteTool(Long id);
    boolean existsBySlug(String slug);
}
