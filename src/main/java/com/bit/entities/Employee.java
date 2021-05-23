package com.bit.entities;

import com.bit.enums.Gender;
import com.bit.enums.Role;
import com.bit.models.AuditModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "employees")
public class Employee extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 25, min = 3)
    @Column(name = "full_name", length = 25, unique = true, nullable = false)
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Gender gender;

    @Email
    @NotNull
    @Size(max = 50, min = 8)
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @NotNull
    @Size(max = 10, min = 5)
    @Column(length = 10, nullable = false)
    private String password;

    @NotNull
    @Size(max = 15, min = 9)
    @Column(length = 15, unique = true, nullable = false)
    private String phone;

    @NotNull
    @Size(max = 255, min = 5)
    @Column(nullable = false)
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}