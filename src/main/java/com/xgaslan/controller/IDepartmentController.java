package com.xgaslan.controller;

import com.xgaslan.data.models.DepartmentModel;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IDepartmentController {
    ResponseEntity<DepartmentModel.DepartmentViewModel> getById(UUID id);
}
