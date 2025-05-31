package com.xgaslan.repositories;

import com.xgaslan.data.entities.Employee;
import com.xgaslan.data.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("SELECT new com.xgaslan.data.models.EmployeeModel.EmployeeViewModel(e.id, e.name, e.lastName, e.department.id) " +
            "FROM Employee e WHERE e.id = :id")
    Optional<EmployeeModel.EmployeeViewModel> findEmployeeViewModelById(@Param("id") UUID id);

    Optional<Employee> findEmployeeById(UUID id);
}
