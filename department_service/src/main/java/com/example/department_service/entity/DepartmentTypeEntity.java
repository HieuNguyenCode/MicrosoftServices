package com.example.department_service.entity;

import com.example.department_service.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "department")
public class DepartmentTypeEntity {
    @Id
    @Column(name = "IDDepartment", columnDefinition = "CHAR(36)")
    private UUID IDDepartment;

    @Column(name = "Name", nullable = false, unique = true, length = 100)
    private String Name;

    @Column(name = "total_member")
    private Integer TotalMember;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    private DepartmentType Type;

    @Column(name = "created_at")
    private LocalDateTime CreatedAt;

    @PrePersist
    protected void onCreate() {
        this.IDDepartment = UUID.randomUUID();
        this.CreatedAt = LocalDateTime.now();
    }
}
