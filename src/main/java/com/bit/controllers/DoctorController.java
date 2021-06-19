package com.bit.controllers;

import com.bit.dtos.doctor.CreateDoctorDto;
import com.bit.dtos.doctor.ShowDoctorDto;
import com.bit.dtos.doctor.UpdateDoctorDto;
import com.bit.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public List<ShowDoctorDto> findAllDoctors() {

        return doctorService.findAllDoctors();
    }

    @PostMapping("/doctors")
    public ShowDoctorDto createNewDoctor(@RequestBody CreateDoctorDto doctorRequest) {

        return doctorService.createNewDoctor(doctorRequest);
    }

    @PutMapping("/doctors/{id}")
    public ShowDoctorDto updateDoctor(@PathVariable("id") Long doctorId,
                                          @RequestBody UpdateDoctorDto doctorRequest) {

        return doctorService.updateDoctor(doctorId, doctorRequest);
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity deleteDoctor(@PathVariable("id") Long doctorId) {

        return doctorService.deleteDoctor(doctorId);
    }
}
