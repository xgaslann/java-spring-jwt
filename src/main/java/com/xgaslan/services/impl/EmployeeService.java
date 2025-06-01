package com.xgaslan.services.impl;

import com.xgaslan.data.entities.Employee;
import com.xgaslan.data.mappers.EmployeeMapper;
import com.xgaslan.data.models.EmployeeModel;
import com.xgaslan.repositories.IEmployeeRepository;
import com.xgaslan.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository _repository;

    @Autowired
    public EmployeeService(IEmployeeRepository repository) {
        _repository = repository;
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        return _repository.findEmployeeById(id);
    }

    @Override
    public Optional<EmployeeModel.EmployeeViewModel> getEmployeeViewModelById(String id) {
        return _repository.findEmployeeById(id).map(
                EmployeeMapper::toEmployeeViewModel);
    }
}
