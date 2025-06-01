package com.xgaslan.controller;

import com.xgaslan.data.models.EmployeeModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IEmployeeController {
    ResponseEntity<EmployeeModel.EmployeeViewModel> getById(String id);
}
