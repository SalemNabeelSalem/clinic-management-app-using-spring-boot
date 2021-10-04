package com.bit.controllers;

import com.bit.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/users-chart-data")
    public ResponseEntity getUsersChartData() {

        return new ResponseEntity<>(reportsService.getUsersChartData(), HttpStatus.OK);
    }
}