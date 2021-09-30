package com.bit.dtos.laboratory;

import com.bit.enums.DoctorType;
import com.bit.enums.Gender;
import lombok.Data;

@Data
public class UpdateLaboratoryDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private String email;

    private DoctorType type;
}