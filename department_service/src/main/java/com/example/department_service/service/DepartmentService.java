package com.example.department_service.service;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.DepartmentTypeEntity;
import com.example.department_service.interfaces.IDepartment;
import com.example.department_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartment {
    private  final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentTypeEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream()
                .map(department -> new DepartmentDto(
                        department.getName(),
                        department.getType(),
                        department.getCreatedAt()))
                .toList();
    }
}
