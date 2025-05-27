package com.xgaslan.data.entities;

import com.xgaslan.data.entities.base.BaseUUIDKeyTextEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "department_text",
        indexes = {
                @Index(columnList = "department_id"),
                @Index(columnList = "department_id,languageId", unique = true)
        }
)
@Data
public class DepartmentText extends BaseUUIDKeyTextEntity {
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "department_id", nullable = false)
        private Department department;
}