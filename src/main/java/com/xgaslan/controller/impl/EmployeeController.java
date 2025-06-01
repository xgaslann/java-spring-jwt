package com.xgaslan.controller.impl;

import com.xgaslan.controller.IEmployeeController;
import com.xgaslan.data.models.EmployeeModel;
import com.xgaslan.services.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController implements IEmployeeController{

    private final IEmployeeService _employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        _employeeService = employeeService;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel.EmployeeViewModel> getById(@PathVariable String id) {
        return ResponseEntity.ok(_employeeService.getEmployeeViewModelById(id).orElse(null));
    }
}
