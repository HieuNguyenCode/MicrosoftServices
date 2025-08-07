package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    public List<DepartmentDto> getListDepartmen() {
        return departmentService.getAllDepartments();
    }
}
