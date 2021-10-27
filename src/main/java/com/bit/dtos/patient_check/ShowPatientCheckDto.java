package com.bit.dtos.patient_check;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShowPatientCheckDto {

    private Long id;

    private String checking;

    private String prescription;

    private String remarks;

    private String dignostic;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long doctorId;

    private Long reservationId;
}