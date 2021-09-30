package com.bit.dtos.laboratory;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class CreateLaboratoryDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private String email;

    private String userName;

    private String password;
}