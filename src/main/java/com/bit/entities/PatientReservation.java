package com.bit.entities;

import com.bit.enums.Gender;
import com.bit.models.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "patient_reservations")
public class PatientReservation extends AuditModel {

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
    @Size(max = 15, min = 9)
    @Column(length = 15, unique = true, nullable = false)
    private String phone;

    @Lob
    @NotNull
    private String feeling;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Employee employee;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}