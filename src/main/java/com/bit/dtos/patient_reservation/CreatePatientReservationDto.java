package com.bit.dtos.patient_reservation;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class CreatePatientReservationDto {

    private String fullName;

    private Gender gender;

    private String email;

    private String phone;

    private String feeling;

    private Long employeeId;
}