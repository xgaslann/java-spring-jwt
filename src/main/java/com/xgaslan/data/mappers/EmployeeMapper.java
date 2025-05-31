package com.xgaslan.data.mappers;

import com.xgaslan.data.entities.Employee;
import com.xgaslan.data.models.EmployeeModel;

public class EmployeeMapper {

    public static EmployeeModel.EmployeeViewModel toEmployeeViewModel(Employee employee) {
        if (employee == null) {
            return null;
        }

        return new EmployeeModel.EmployeeViewModel(
                employee.getId(),
                employee.getName(),
                employee.getLastName(),
                employee.getDepartment().getId());
    }
}
