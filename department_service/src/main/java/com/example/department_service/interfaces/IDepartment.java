package com.example.department_service.interfaces;

import com.example.department_service.dto.DepartmentDto;

import java.util.List;

public interface IDepartment {
    List<DepartmentDto> getAllDepartments();
}
