package com.bit.services;

import com.bit.dtos.patient_reservation.CreatePatientReservationDto;
import com.bit.dtos.patient_reservation.ShowPatientReservationDto;
import com.bit.dtos.patient_reservation.UpdatePatientReservationDto;
import com.bit.entities.PatientReservation;
import com.bit.entities.Receptionist;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.PatientReservationRepository;
import com.bit.repositories.ReceptionistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientReservationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PatientReservationRepository patientReservationRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    public List<ShowPatientReservationDto> findAllPatientsReservationsByReceptionistId(Long receptionistId) {

        Receptionist receptionist = receptionistRepository.findById(receptionistId).orElseThrow(
            () -> new ResourceNotFoundException("receptionist with id: [" + receptionistId + "] is not found.")
        );

        List<PatientReservation> sortedPatientsReservationsList = patientReservationRepository.findAllByReceptionist(receptionist)
            .stream().sorted(
                (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
            ).collect(Collectors.toList());

        return sortedPatientsReservationsList.stream().map(
            obj -> modelMapper.map(obj, ShowPatientReservationDto.class)
        ).collect(Collectors.toList());
    }

    public ShowPatientReservationDto createNewPatientReservationByReceptionistId(
            Long receptionistId, CreatePatientReservationDto patientReservationInput) {

        if (receptionistRepository.findById(receptionistId).isEmpty()) {

            throw new ResourceNotFoundException("employee with id: [" + receptionistId + "] is not found.");
        }

        Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

        PatientReservation patientReservation = new PatientReservation();

        patientReservation.setFullName(patientReservationInput.getFullName());
        patientReservation.setGender(patientReservationInput.getGender());
        patientReservation.setAge(patientReservationInput.getAge());
        patientReservation.setEmail(patientReservationInput.getEmail());
        patientReservation.setPhone(patientReservationInput.getPhone());
        patientReservation.setFeeling(patientReservationInput.getFeeling());
        patientReservation.setReceptionist(receptionist);

        return modelMapper.map(
                patientReservationRepository.save(patientReservation), ShowPatientReservationDto.class
        );
    }

    public ShowPatientReservationDto updatePatientReservation(Long patientReservationId,
                                                              UpdatePatientReservationDto patientReservationRequest) {

        if (patientReservationRepository.findById(patientReservationId).isEmpty()) {

            throw new ResourceNotFoundException(
                "patient reservationId with id: [" + patientReservationId + "] is not found."
            );
        }

        PatientReservation patientReservationData = patientReservationRepository.findById(patientReservationId).get();

        Long employeeId = patientReservationRequest.getEmployeeId();

        if (receptionistRepository.findById(employeeId).isEmpty()) {

            throw new ResourceNotFoundException("employee with id: [" + employeeId + "] is not found.");
        }

        Receptionist receptionistData = receptionistRepository.findById(employeeId).get();

        patientReservationData.setFullName(patientReservationRequest.getFullName());
        patientReservationData.setGender(patientReservationRequest.getGender());
        patientReservationData.setEmail(patientReservationRequest.getEmail());
        patientReservationData.setPhone(patientReservationRequest.getPhone());
        patientReservationData.setFeeling(patientReservationRequest.getFeeling());
        patientReservationData.setReceptionist(receptionistData);

        return modelMapper.map(
            patientReservationRepository.save(patientReservationData), ShowPatientReservationDto.class
        );
    }

    public ResponseEntity deletePatientReservation(Long patientReservationId) {

        if (patientReservationRepository.findById(patientReservationId).isEmpty()) {

            throw new ResourceNotFoundException(
                "patient reservationId with id: [" + patientReservationId + "] is not found."
            );
        }

        PatientReservation patientReservationData = patientReservationRepository.findById(patientReservationId).get();

        patientReservationRepository.save(patientReservationData);

        return ResponseEntity.ok().build();
    }
}