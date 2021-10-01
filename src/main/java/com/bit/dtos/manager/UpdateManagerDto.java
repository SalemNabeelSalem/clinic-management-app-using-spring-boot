package com.bit.dtos.manager;

import com.bit.enums.Gender;
import lombok.Data;

@Data
public class UpdateManagerDto {

    private String fullName;

    private Gender gender;

    private String phone;

    private String email;
}