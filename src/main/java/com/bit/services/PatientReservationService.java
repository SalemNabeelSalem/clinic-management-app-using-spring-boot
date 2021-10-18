package com.bit.services;

import com.bit.dtos.patient_check.ShowPatientCheckDto;
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

    @Autowired
    private PatientCheckService patientCheckService;

    public List<ShowPatientReservationDto> findAllPatientsReservations() {

        return patientReservationRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList()).stream().map(
            obj -> modelMapper.map(obj, ShowPatientReservationDto.class)
        ).collect(Collectors.toList());
    }

    public List<ShowPatientReservationDto> findAllPatientsReservationsOfReceptionist(Long receptionistId) {

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

    public ShowPatientReservationDto createNewPatientReservation(
            Long receptionistId, CreatePatientReservationDto patientReservationInput
    ) {

        Receptionist receptionist = receptionistRepository.findById(receptionistId).orElseThrow(
            () -> new ResourceNotFoundException("employee with id: [" + receptionistId + "] is not found.")
        );

        PatientReservation patientReservation = new PatientReservation();

        patientReservation.setFullName(patientReservationInput.getFullName());
        patientReservation.setGender(patientReservationInput.getGender());
        patientReservation.setAge(patientReservationInput.getAge());
        patientReservation.setEmail(patientReservationInput.getEmail());
        patientReservation.setPhone(patientReservationInput.getPhone());
        patientReservation.setFeeling(patientReservationInput.getFeeling());
        patientReservation.setReceptionist(receptionist);
        patientReservation.setDoctorId(patientReservationInput.getDoctorId());

        return modelMapper.map(
            patientReservationRepository.save(patientReservation), ShowPatientReservationDto.class
        );
    }

    public ShowPatientReservationDto findPatientReservationById(Long patientReservationId) {

        PatientReservation patientReservation = patientReservationRepository.findById(patientReservationId).orElseThrow(
            () -> new ResourceNotFoundException("patient reservationId with id: [" + patientReservationId + "] is not found.")
        );

        return modelMapper.map(patientReservation, ShowPatientReservationDto.class);
    }

    public ShowPatientReservationDto updatePatientReservation(Long patientReservationId, UpdatePatientReservationDto patientReservationInput) {

        PatientReservation patientReservation = patientReservationRepository.findById(patientReservationId).orElseThrow(
            () -> new ResourceNotFoundException("patient reservationId with id: [" + patientReservationId + "] is not found.")
        );

        patientReservation.setFullName(patientReservationInput.getFullName());
        patientReservation.setGender(patientReservationInput.getGender());
        patientReservation.setAge(patientReservationInput.getAge());
        patientReservation.setEmail(patientReservationInput.getEmail());
        patientReservation.setPhone(patientReservationInput.getPhone());
        patientReservation.setFeeling(patientReservationInput.getFeeling());
        patientReservation.setDoctorId(patientReservationInput.getDoctorId());

        return modelMapper.map(
            patientReservationRepository.save(patientReservation), ShowPatientReservationDto.class
        );
    }

    public ShowPatientCheckDto findPatientCheckByReservationId(Long reservationId) {

        return patientCheckService.findPatientCheckByReservationId(reservationId);
    }
}