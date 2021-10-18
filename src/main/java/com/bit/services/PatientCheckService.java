package com.bit.services;

import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.entities.PatientCheck;
import com.bit.repositories.PatientCheckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientCheckService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientCheckRepository patientCheckRepository;

    public ShowPatientCheckDto findPatientCheckByReservationId(Long reservationId) {

        PatientCheck patientCheck = patientCheckRepository.findByReservationId(reservationId).orElse(
            new PatientCheck()
        );

        return modelMapper.map(patientCheck, ShowPatientCheckDto.class);
    }
}