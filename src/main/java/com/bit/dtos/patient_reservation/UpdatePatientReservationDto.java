package com.bit.dtos.patient_reservation;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class UpdatePatientReservationDto {

    private String fullName;

    private Gender gender;

    private Integer age;

    private String phone;

    private String email;

    private String feeling;

    private Long doctorId;
}