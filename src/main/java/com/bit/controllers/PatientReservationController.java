package com.bit.controllers;

import com.bit.dtos.patient_reservation.CreatePatientReservationDto;
import com.bit.dtos.patient_reservation.ShowPatientReservationDto;
import com.bit.dtos.patient_reservation.UpdatePatientReservationDto;
import com.bit.services.PatientReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/patient_reservation/{id}")
    public ShowPatientReservationDto updatePatientReservation(
            @PathVariable("id") Long patientReservationId,
            @RequestBody UpdatePatientReservationDto patientReservationRequest) {

        return patientReservationService.updatePatientReservation(
                patientReservationId, patientReservationRequest
        );
    }

    @DeleteMapping("/patient_reservation/{id}")
    public ResponseEntity deletePatientReservation(@PathVariable("id") Long patientReservationId) {

        return patientReservationService.deletePatientReservation(patientReservationId);
    }
}