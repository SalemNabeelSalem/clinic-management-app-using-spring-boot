package com.bit.dtos.manager;

import com.bit.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShowManagerDto {

    private Long id;

    private String fullName;

    private Gender gender;

    private String phone;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    private Boolean isActive;
}