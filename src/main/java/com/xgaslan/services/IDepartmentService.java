package com.xgaslan.services;

import com.xgaslan.data.entities.Department;
import com.xgaslan.data.models.DepartmentModel;

import java.util.Optional;
import java.util.UUID;

public interface IDepartmentService {
    Optional<Department> findDepartmentByIdWithTexts(UUID id);

    Optional<Department> findDepartmentById(UUID id);

    Optional<DepartmentModel.DepartmentViewModel> getDepartmentViewModelById(UUID id);
}
