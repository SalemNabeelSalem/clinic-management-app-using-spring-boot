package com.bit.dtos.employee;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class UpdateEmployeeDto {

    private String fullName;

    private Gender gender;

    private String email;

    private String phone;

    private String image;
}
