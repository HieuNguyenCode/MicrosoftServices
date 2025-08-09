package com.example.department_service.entity;

import com.example.department_service.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentTypeEntity {
    @Id
    @Column(name = "IdDepartment", columnDefinition = "CHAR(36)")
    private UUID IdDepartment;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String Name;

    @Column(name = "total_member")
    private Integer TotalMember;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DepartmentType Type;

    @Column(name = "created_at")
    private LocalDateTime CreatedAt;

    @PrePersist
    protected void onCreate() {
        this.IdDepartment = UUID.randomUUID();
        this.CreatedAt = LocalDateTime.now();
    }
}
