package com.xgaslan.data.entities;

import com.xgaslan.data.entities.base.BaseUUIDKeyTextEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "department_text",
        indexes = {
                @Index(columnList = "objectId"),
                @Index(columnList = "objectId,languageId", unique = true)
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentText extends BaseUUIDKeyTextEntity {}