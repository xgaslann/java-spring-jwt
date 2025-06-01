package com.xgaslan.repositories;

import com.xgaslan.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeById(String id);
}
