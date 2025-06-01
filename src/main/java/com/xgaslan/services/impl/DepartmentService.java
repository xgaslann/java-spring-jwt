package com.xgaslan.services.impl;

import com.xgaslan.data.entities.Department;
import com.xgaslan.data.mappers.DepartmentMapper;
import com.xgaslan.data.models.DepartmentModel;
import com.xgaslan.repositories.IDepartmentRepository;
import com.xgaslan.services.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService implements IDepartmentService{
    private final IDepartmentRepository _repository;

    public DepartmentService(IDepartmentRepository repository) {
        _repository = repository;
    }

    @Override
    public Optional<Department> findDepartmentByIdWithTexts(UUID id) {
        return _repository.findByIdWithTexts(id);
    }

    @Override
    public Optional<Department> findDepartmentById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<DepartmentModel.DepartmentViewModel> getDepartmentViewModelById(UUID id) {
        return findDepartmentByIdWithTexts(id).map(DepartmentMapper::toDepartmentViewModel);
    }
}
