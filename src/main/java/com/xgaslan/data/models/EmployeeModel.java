package com.xgaslan.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class EmployeeModel {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeViewModel{
        private String id;

        private String name;

        private String lastName;

        private UUID departmentId;
    }
}