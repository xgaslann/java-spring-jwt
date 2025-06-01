package com.xgaslan.services;

import com.xgaslan.data.entities.Employee;
import com.xgaslan.data.models.EmployeeModel;

import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {
    Optional<Employee> findEmployeeById(String id);

    Optional<EmployeeModel.EmployeeViewModel> getEmployeeViewModelById(String id);
}
