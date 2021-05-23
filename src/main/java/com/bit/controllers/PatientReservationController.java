package com.bit.controllers;

import com.bit.dtos.patient_reservation.CreatePatientReservationDto;
import com.bit.dtos.patient_reservation.ShowPatientReservationDto;
import com.bit.services.PatientReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PatientReservationController {

    @Autowired
    private PatientReservationService patientReservationService;

    @GetMapping("/patient_reservation")
    public List<ShowPatientReservationDto> findAllPatientsReservations() {

        return patientReservationService.findAllPatientsReservations();
    }

    @PostMapping("/patient_reservation")
    public ShowPatientReservationDto createNewPatientReservation(
            @RequestBody CreatePatientReservationDto PatientReservationRequest) {

        return patientReservationService.createNewPatientReservation(PatientReservationRequest);
    }
}