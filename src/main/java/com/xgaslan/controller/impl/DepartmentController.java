package com.xgaslan.controller.impl;

import com.xgaslan.controller.IDepartmentController;
import com.xgaslan.data.models.DepartmentModel;
import com.xgaslan.services.IDepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController implements IDepartmentController {

    private final IDepartmentService _service;

    public DepartmentController(IDepartmentService service) {
        _service = service;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentModel.DepartmentViewModel> getById(@Valid @NotEmpty @PathVariable UUID id) {
        return ResponseEntity.ok(_service.getDepartmentViewModelById(id).orElse(null));
    }
}
