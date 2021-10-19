package com.bit.entities;

import com.bit.models.AuditModel;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "patients_checks")
public class PatientCheck extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String checking;

    private String prescription;

    private String remarks;

    private LocalDate date;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "reservation_id")
    private Long reservationId;
}