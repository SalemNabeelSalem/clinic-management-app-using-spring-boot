package com.bit.dtos.patient_check;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePatientCheckDto {

    private String check;

    private String prescription;

    private String remarks;

    private LocalDate date;

    private Long doctorId;

    private Long reservationId;
}