package com.bit.dtos.patient_check;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowPatientCheckDto {

    private Long id;

    private String checking;

    private String prescription;

    private String remarks;

    private Long doctorId;

    private Long reservationId;
}