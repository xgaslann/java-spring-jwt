package com.xgaslan.data.entities.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    @LastModifiedDate
    private LocalDateTime changedAt;

    private UUID changedBy;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (createdBy == null) {
            createdBy = UUID.fromString("00000000-0000-0000-0000-000000000000"); // örnek dummy uuid
        }
        // createdBy will be set by the service layer or security context
    }

    @PreUpdate
    public void preUpdate() {
        changedAt = LocalDateTime.now();
        if (changedBy == null) {
            changedBy = UUID.fromString("00000000-0000-0000-0000-000000000000"); // örnek dummy uuid
        }
        // changedBy will be set by the service layer or security context
    }
}