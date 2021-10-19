package com.bit.controllers;

import com.bit.dtos.patient_check.CreatePatientCheckDto;
import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.services.PatientCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PatientCheckController {

    private final PatientCheckService patientCheckService;

    @PostMapping("/patients-checks")
    public ShowPatientCheckDto createNewPatientCheck(@RequestBody CreatePatientCheckDto patientCheckRequest) {

        return patientCheckService.createNewPatientCheck(patientCheckRequest);
    }
}