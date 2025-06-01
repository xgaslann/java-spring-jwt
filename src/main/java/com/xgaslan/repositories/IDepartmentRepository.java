package com.xgaslan.repositories;

import com.xgaslan.data.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, UUID> {

    @Query("SELECT d FROM Department d JOIN FETCH d.texts WHERE d.id = :id")
    Optional<Department> findByIdWithTexts(@Param("id") UUID id);

    Optional<Department> findDepartmentById(UUID id);
}
