package com.example.department_service.repository;

import com.example.department_service.entity.DepartmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<DepartmentTypeEntity, UUID> {
}
