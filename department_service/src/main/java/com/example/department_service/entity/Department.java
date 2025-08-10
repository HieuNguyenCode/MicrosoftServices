package com.example.department_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @jakarta.validation.constraints.Size(max = 36)
    @ColumnDefault("uuid()")
    @Column(name = "Id_department", nullable = false, length = 36)
    private String idDepartment;

    @jakarta.validation.constraints.Size(max = 100)
    @jakarta.validation.constraints.NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "total_member")
    private Integer totalMember;

    @jakarta.validation.constraints.NotNull
    @Lob
    @Column(name = "type", nullable = false)
    private String type;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private Instant createdAt;

}