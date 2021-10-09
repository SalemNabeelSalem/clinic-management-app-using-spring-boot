package com.bit.services;

import com.bit.dtos.doctor.CreateDoctorDto;
import com.bit.dtos.doctor.DoctorsListDto;
import com.bit.dtos.doctor.ShowDoctorDto;
import com.bit.dtos.doctor.UpdateDoctorDto;
import com.bit.entities.Doctor;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<ShowDoctorDto> findAllDoctors() {

        List<Doctor> sortedDoctorsList = doctorRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedDoctorsList.stream().map(
            obj -> modelMapper.map(obj, ShowDoctorDto.class)
        ).collect(Collectors.toList());
    }

    public ShowDoctorDto createNewDoctor(CreateDoctorDto doctorInput) {

        Doctor doctor = new Doctor();

        doctor.setFullName(doctorInput.getFullName());

        doctor.setGender(doctorInput.getGender());

        doctor.setPhone(doctorInput.getPhone());

        doctor.setEmail(doctorInput.getEmail());

        doctor.setType(doctorInput.getType());

        doctor.setUserName(doctorInput.getUserName());

        doctor.setPassword(doctorInput.getPassword());

        doctor.setIsActive(true);

        return modelMapper.map(
            doctorRepository.save(doctor), ShowDoctorDto.class
        );
    }

    public ShowDoctorDto findDoctorById(Long doctorId) {
        return modelMapper.map(doctorRepository.findById(doctorId).orElseThrow(
            () -> new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.")
        ), ShowDoctorDto.class);
    }

    public ShowDoctorDto updateDoctor(Long doctorId, UpdateDoctorDto doctorInput) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
            () -> new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.")
        );

        doctor.setFullName(doctorInput.getFullName());

        doctor.setGender(doctorInput.getGender());

        doctor.setPhone(doctorInput.getPhone());

        doctor.setEmail(doctorInput.getEmail());

        doctor.setType(doctorInput.getType());

        return modelMapper.map(
            doctorRepository.save(doctor), ShowDoctorDto.class
        );
    }

    public ResponseEntity deactivateDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
            () -> new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.")
        );

        doctor.setIsActive(false);

        doctorRepository.save(doctor);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity activateDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
            () -> new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.")
        );

        doctor.setIsActive(true);

        doctorRepository.save(doctor);

        return ResponseEntity.ok().build();
    }

    public List<DoctorsListDto> findAllDoctorsList() {
        List<Doctor> sortedDoctorsList = doctorRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedDoctorsList.stream().map(
            obj -> {
                DoctorsListDto doctorsListDto = new DoctorsListDto();
                doctorsListDto.setId(obj.getId());
                doctorsListDto.setSummary(obj.getFullName().concat(" - " + obj.getType()));
                return doctorsListDto;
            }
        ).collect(Collectors.toList());
    }
}