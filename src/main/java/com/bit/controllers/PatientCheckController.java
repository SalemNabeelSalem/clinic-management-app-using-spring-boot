package com.bit.controllers;

import com.bit.dtos.patient_check.CreatePatientCheckDto;
import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.dtos.patient_check.UpdatePatientCheckLabDto;
import com.bit.services.PatientCheckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class PatientCheckController {

    private final PatientCheckService patientCheckService;

    @GetMapping("/patients-checks")
    public List<ShowPatientCheckDto> findAllPatientsChecks() {

        return patientCheckService.findAllPatientsChecks();
    }

    @PostMapping("/patients-checks")
    public ShowPatientCheckDto createNewPatientCheck(@RequestBody CreatePatientCheckDto patientCheckRequest) {

        return patientCheckService.createNewPatientCheck(patientCheckRequest);
    }

    @PutMapping("/patients-checks/{id}/lab")
    public ShowPatientCheckDto updatePatientCheckFromLab(@PathVariable Long id,
                                                         @RequestBody UpdatePatientCheckLabDto patientCheckRequest) {

        return patientCheckService.updatePatientCheckFromLab(id, patientCheckRequest);
    }
}