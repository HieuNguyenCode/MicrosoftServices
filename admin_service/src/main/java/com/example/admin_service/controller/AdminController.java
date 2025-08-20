package com.example.admin_service.controller;

import com.example.admin_service.Dto.DepartmentDTO;
import com.example.admin_service.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments() {
        return adminService.getDepartments();
    }
}
