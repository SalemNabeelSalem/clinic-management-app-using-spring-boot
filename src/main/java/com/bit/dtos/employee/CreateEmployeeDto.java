package com.bit.dtos.employee;

import com.bit.enums.Gender;
import com.bit.enums.Role;
import lombok.Data;

@Data
public class CreateEmployeeDto {

    private String fullName;

    private Gender gender;

    private String email;

    private String password;

    private String phone;

    private String image;

    private Role role;
}