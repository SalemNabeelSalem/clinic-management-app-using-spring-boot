package com.bit.services;

import com.bit.dtos.patient_check.CreatePatientCheckDto;
import com.bit.dtos.patient_check.ShowPatientCheckDto;
import com.bit.dtos.patient_check.UpdatePatientCheckLabDto;
import com.bit.entities.PatientCheck;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.PatientCheckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public ShowPatientCheckDto createNewPatientCheck(CreatePatientCheckDto patientCheckInput) {

        PatientCheck patientCheck = new PatientCheck();

        patientCheck.setChecking(patientCheckInput.getCheck());

        patientCheck.setPrescription(patientCheckInput.getPrescription());

        patientCheck.setRemarks(patientCheckInput.getRemarks());

        patientCheck.setDate(patientCheckInput.getDate());

        patientCheck.setDoctorId(patientCheckInput.getDoctorId());

        patientCheck.setReservationId(patientCheckInput.getReservationId());

        return modelMapper.map(patientCheckRepository.save(patientCheck), ShowPatientCheckDto.class);
    }

    public List<ShowPatientCheckDto> findAllAllPatientsChecksOfDoctor(Long doctorId) {

        List<PatientCheck> patientsChecksList = patientCheckRepository.findAllByDoctorId(doctorId)
            .stream().sorted(
                (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
            ).collect(Collectors.toList());;

        return patientsChecksList.stream().map(
            obj -> modelMapper.map(obj, ShowPatientCheckDto.class)
        ).collect(Collectors.toList());
    }

    public List<ShowPatientCheckDto> findAllPatientsChecks() {

        List<PatientCheck> patientsChecksList = patientCheckRepository.findAll()
            .stream().sorted(
                (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
            ).collect(Collectors.toList());;

        return patientsChecksList.stream().map(
            obj -> modelMapper.map(obj, ShowPatientCheckDto.class)
        ).collect(Collectors.toList());
    }

    public ShowPatientCheckDto updatePatientCheckFromLab(Long id, UpdatePatientCheckLabDto patientCheckRequest) {

        PatientCheck patientCheck = patientCheckRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("patient Check with id: [" + id + "] is not found.")
        );

        patientCheck.setDignostic(patientCheckRequest.getDignostic());

        return modelMapper.map(patientCheckRepository.save(patientCheck), ShowPatientCheckDto.class);
    }
}