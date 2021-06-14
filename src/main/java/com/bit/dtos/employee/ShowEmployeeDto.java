package com.bit.dtos.employee;

import com.bit.enums.Gender;
import com.bit.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShowEmployeeDto {

    private Long id;

    private String fullName;

    private Gender gender;

    private String phone;

    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;
}