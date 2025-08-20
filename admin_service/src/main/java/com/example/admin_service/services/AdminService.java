package com.example.admin_service.services;

import com.example.admin_service.Dto.DepartmentDTO;
import com.example.admin_service.feignclient.DepartmentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final DepartmentFeignClient departmentFeignClient;

    public List<DepartmentDTO> getDepartments() {
        return departmentFeignClient.getAllDepartments();
    }
}
