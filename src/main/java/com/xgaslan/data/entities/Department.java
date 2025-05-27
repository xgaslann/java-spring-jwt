package com.xgaslan.data.entities;

import com.xgaslan.data.entities.base.BaseUUIDKeyEntity;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.List;

@Data
@Entity
@Table(
        name = "department",
        indexes = @Index(columnList = "isDeleted", name = "idx_department_is_deleted")
)
@FilterDef(name = "activeFilter", parameters = @ParamDef(name = "isDeleted", type = boolean.class))
@Filter(name = "activeFilter", condition = "is_deleted = :isDeleted")
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseUUIDKeyEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "object_id", referencedColumnName = "id")
    private List<DepartmentText> texts;

}
