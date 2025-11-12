package com.example.authapp.controller;

import com.example.authapp.dto.ToolDTO;
import com.example.authapp.service.ToolExecutionService;
import com.example.authapp.service.ToolService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolExecutionService toolExecutionService;
    private final ToolService toolService;

    public ToolController(ToolExecutionService toolExecutionService, ToolService toolService) {
        this.toolExecutionService = toolExecutionService;
        this.toolService = toolService;
    }

    @PostMapping
    public ResponseEntity<ToolDTO> createTool(@Valid @RequestBody ToolDTO toolDTO) {
        ToolDTO createdTool = toolService.createTool(toolDTO);
        return new ResponseEntity<>(createdTool, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolDTO> getToolById(@PathVariable Long id) {
        ToolDTO tool = toolService.getToolById(id);
        return ResponseEntity.ok(tool);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ToolDTO> getToolBySlug(@PathVariable String slug) {
        ToolDTO tool = toolService.getToolBySlug(slug);
        return ResponseEntity.ok(tool);
    }

    @GetMapping
    public ResponseEntity<Page<ToolDTO>> getAllTools(Pageable pageable) {
        Page<ToolDTO> tools = toolService.getAllTools(pageable);
        return ResponseEntity.ok(tools);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToolDTO> updateTool(
            @PathVariable Long id,
            @Valid @RequestBody ToolDTO toolDTO) {
        ToolDTO updatedTool = toolService.updateTool(id, toolDTO);
        return ResponseEntity.ok(updatedTool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id) {
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/slug/{slug}")
    public ResponseEntity<Boolean> checkSlugExists(@PathVariable String slug) {
        boolean exists = toolService.existsBySlug(slug);
        return ResponseEntity.ok(exists);
    }

    // Execute tool
    @PostMapping("/{slug}/execute")
    public ResponseEntity<?> executeTool(
            @PathVariable String slug,
            @RequestParam("files") MultipartFile[] files) {

        String result = toolExecutionService.executeTool(slug, files);
        return ResponseEntity.ok().body(result);
    }

}
