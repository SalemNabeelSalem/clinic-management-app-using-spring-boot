package com.bit.dtos.reports;

import lombok.Data;

@Data
public class UsersChartData {

    private Integer doctors;

    private Integer receptionists;

    private Integer laboratories;

    private Integer managers;
}