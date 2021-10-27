package com.bit.controllers;

import com.bit.dtos.doctor.CreateDoctorDto;
import com.bit.dtos.doctor.DoctorsListDto;
import com.bit.dtos.doctor.ShowDoctorDto;
import com.bit.dtos.doctor.UpdateDoctorDto;
import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.dtos.patient_reservation.ShowPatientReservationDto;
import com.bit.entities.Doctor;
import com.bit.services.DoctorService;
import com.bit.services.PatientCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientCheckService patientCheckService;

    @GetMapping("/doctors")
    public List<ShowDoctorDto> findAllDoctors() {

        return doctorService.findAllDoctors();
    }

    @PostMapping("/doctors")
    public ShowDoctorDto createNewDoctor(@RequestBody CreateDoctorDto doctorRequest) {

        return doctorService.createNewDoctor(doctorRequest);
    }

    @GetMapping("/doctors/{id}")
    public ShowDoctorDto findDoctorById(@PathVariable("id") Long doctorId) {

        return doctorService.findDoctorById(doctorId);
    }

    @PutMapping("/doctors/{id}")
    public ShowDoctorDto updateDoctor(@PathVariable("id") Long doctorId,
                                      @RequestBody UpdateDoctorDto doctorRequest) {

        return doctorService.updateDoctor(doctorId, doctorRequest);
    }

    @DeleteMapping("/doctors/{id}/deactivate")
    public Doctor deactivateReceptionist(@PathVariable("id") Long doctorId) {

        return doctorService.deactivateDoctor(doctorId);
    }

    @PutMapping("/doctors/{id}/activate")
    public Doctor activateReceptionist(@PathVariable("id") Long doctorId) {

        return doctorService.activateDoctor(doctorId);
    }

    @GetMapping("/doctors-list")
    public List<DoctorsListDto> findAllDoctorsList() {

        return doctorService.findAllDoctorsList();
    }

    @GetMapping("/doctors/{id}/patients-reservations")
    public List<ShowPatientReservationDto> findAllPatientsReservationsOfDoctor(@PathVariable Long id) {

        return doctorService.findAllPatientsReservationsOfDoctor(id);
    }

    @GetMapping("/doctors/{id}/patients-checks")
    public List<ShowPatientCheckDto> findAllPatientsChecksOfDoctor(@PathVariable Long id) {

        return patientCheckService.findAllAllPatientsChecksOfDoctor(id);
    }
}