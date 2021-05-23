package com.bit.dtos.employee;

import com.bit.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class ShowEmployeeDto {

    private Long id;

    private String fullName;

    private Gender gender;

    private String email;

    private String phone;

    private String image;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isActive;
}
