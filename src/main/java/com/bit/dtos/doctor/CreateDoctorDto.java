package com.bit.dtos.doctor;

import com.bit.enums.DoctorType;
import com.bit.enums.Gender;
import lombok.Data;

@Data
public class CreateDoctorDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private DoctorType type;

    private String username;

    private String password;
}
