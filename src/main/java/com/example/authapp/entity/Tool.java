package com.example.authapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tools")
public class Tool {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "tool_name", nullable = false, length = 100)
    private String toolName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String slug;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Tool(Long id, String toolName, String description, String slug, boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.toolName = toolName;
        this.description = description;
        this.slug = slug;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }
}
