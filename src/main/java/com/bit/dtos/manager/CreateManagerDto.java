package com.bit.dtos.manager;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class CreateManagerDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private String email;

    private String userName;

    private String password;
}