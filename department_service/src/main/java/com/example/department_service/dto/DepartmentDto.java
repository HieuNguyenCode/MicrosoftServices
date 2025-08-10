package com.example.department_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
public class DepartmentDto {
    private String Name;
    private String Type;
    private Instant CreatedAt;
}
