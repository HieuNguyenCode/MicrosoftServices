package com.example.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "refreshtoken")
public class Refreshtoken {
    @Id
    @Size(max = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    @SequenceGenerator(name = "refreshtoken_id_gen", sequenceName = ")")
    @Column(name = "Id_refreshtoken", nullable = false, length = 36)
    private String idRefreshtoken;

    @Size(max = 36)
    @NotNull
    @Column(name = "Keyaccesstoken", nullable = false, length = 36)
    private String keyaccesstoken;

    @Size(max = 36)
    @NotNull
    @Column(name = "Keyrefreshtoken", nullable = false, length = 36)
    private String keyrefreshtoken;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id_user", nullable = false)
    private User idUser;

    @NotNull
    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @ColumnDefault("current_timestamp()")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "created_at")
    private Instant createdAt;

}