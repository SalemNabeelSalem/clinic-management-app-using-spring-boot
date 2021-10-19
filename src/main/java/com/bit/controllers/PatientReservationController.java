package com.bit.controllers;

import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.dtos.patient_reservation.CreatePatientReservationDto;
import com.bit.dtos.patient_reservation.ShowPatientReservationDto;
import com.bit.dtos.patient_reservation.UpdatePatientReservationDto;
import com.bit.services.PatientReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PatientReservationController {

    @Autowired
    private PatientReservationService patientReservationService;

    @GetMapping("/patients-reservations")
    public List<ShowPatientReservationDto> findAllPatientsReservations() {

        return patientReservationService.findAllPatientsReservations();
    }

    @GetMapping("/receptionists/{receptionistId}/patients-reservations")
    public List<ShowPatientReservationDto> findAllPatientsReservationsOfReceptionist(@PathVariable Long receptionistId) {

        return patientReservationService.findAllPatientsReservationsOfReceptionist(receptionistId);
    }

    @PostMapping("/receptionists/{receptionistId}/patients-reservations")
    public ShowPatientReservationDto createNewPatientReservation(
            @PathVariable Long receptionistId, @RequestBody CreatePatientReservationDto patientReservationRequest) {

        return patientReservationService.createNewPatientReservation(receptionistId, patientReservationRequest);
    }

    @GetMapping("/receptionists/{receptionistId}/patients-reservations/{patientReservationId}")
    public ShowPatientReservationDto findPatientReservationById(@PathVariable Long receptionistId, @PathVariable Long patientReservationId) {

        return patientReservationService.findPatientReservationById(patientReservationId);
    }

    @PutMapping("/receptionists/{receptionistId}/patients-reservations/{patientReservationId}")
    public ShowPatientReservationDto updatePatientReservation(
            @PathVariable Long receptionistId, @PathVariable Long patientReservationId,
            @RequestBody UpdatePatientReservationDto patientReservationRequest) {

        return patientReservationService.updatePatientReservation(
            patientReservationId, patientReservationRequest
        );
    }

    @GetMapping("/patients-reservations/{reservationId}/patients-checks")
    public ShowPatientCheckDto findPatientCheckByReservationId(@PathVariable Long reservationId) {

        return patientReservationService.findPatientCheckByReservationId(reservationId);
    }
}