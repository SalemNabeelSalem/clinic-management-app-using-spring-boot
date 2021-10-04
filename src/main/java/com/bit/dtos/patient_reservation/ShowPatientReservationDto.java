package com.bit.dtos.patient_reservation;

import com.bit.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShowPatientReservationDto {

    private Long id;

    private String fullName;

    private Gender gender;

    private Integer age;

    private String email;

    private String phone;

    private String feeling;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    // private String receptionistFullName;
}