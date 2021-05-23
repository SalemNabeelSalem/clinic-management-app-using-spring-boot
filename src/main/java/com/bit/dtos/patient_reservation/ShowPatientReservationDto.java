package com.bit.dtos.patient_reservation;

import com.bit.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class ShowPatientReservationDto {

    private Long id;

    private String fullName;

    private Gender gender;

    private String email;

    private String phone;

    private String feeling;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isActive;
}