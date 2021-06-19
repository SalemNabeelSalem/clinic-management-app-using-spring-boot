package com.bit.dtos.employee;

import com.bit.enums.Gender;
import com.bit.enums.Role;
import lombok.Data;

@Data
public class UpdateEmployeeDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private Role role;

    private String userName;

    private String password;
}
