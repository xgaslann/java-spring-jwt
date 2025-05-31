package com.xgaslan.services;

import com.xgaslan.data.entities.Employee;
import com.xgaslan.data.models.EmployeeModel;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {

    Optional<EmployeeModel.EmployeeViewModel> findEmployeeViewModelById(UUID id);

    Optional<Employee> findEmployeeById(UUID id);
}
