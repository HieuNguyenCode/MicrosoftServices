package com.example.department_service.dto;

import com.example.department_service.enums.DepartmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class DepartmentDto {
    private String Name;
    private DepartmentType Type;
    private LocalDateTime CreatedAt;
}
