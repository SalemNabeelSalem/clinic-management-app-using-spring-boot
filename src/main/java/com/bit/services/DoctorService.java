package com.bit.services;

import com.bit.dtos.doctor.CreateDoctorDto;
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

        List<Doctor> sortedEmployeesList = doctorRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedEmployeesList.stream().map(
            obj -> modelMapper.map(obj, ShowDoctorDto.class)
        ).collect(Collectors.toList());
    }

    public ShowDoctorDto createNewDoctor(CreateDoctorDto doctorRequest) {

        Doctor doctorData = new Doctor();

        doctorData.setFullName(doctorRequest.getFullName());

        doctorData.setGender(doctorRequest.getGender());

        doctorData.setPhone(doctorRequest.getPhone());

        doctorData.setType(doctorRequest.getType());

        doctorData.setUserName(doctorRequest.getUserName());

        doctorData.setPassword(doctorRequest.getPassword());

        return modelMapper.map(
            doctorRepository.save(doctorData), ShowDoctorDto.class
        );
    }

    public ShowDoctorDto updateDoctor(Long doctorId, UpdateDoctorDto doctorRequest) {

        if (doctorRepository.findById(doctorId).isEmpty()) {

            throw new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.");
        }

        Doctor doctorData = doctorRepository.findById(doctorId).get();

        doctorData.setFullName(doctorRequest.getFullName());

        doctorData.setGender(doctorRequest.getGender());

        doctorData.setPhone(doctorRequest.getPhone());

        doctorData.setType(doctorRequest.getType());

        doctorData.setUserName(doctorRequest.getUserName());

        doctorData.setPassword(doctorRequest.getPassword());

        return modelMapper.map(
            doctorRepository.save(doctorData), ShowDoctorDto.class
        );
    }

    public ResponseEntity deleteDoctor(Long doctorId) {

        if (doctorRepository.findById(doctorId).isEmpty()) {

            throw new ResourceNotFoundException("doctor with id: [" + doctorId + "] is not found.");
        }

        Doctor doctorData = doctorRepository.findById(doctorId).get();

        doctorRepository.delete(doctorData);

        return ResponseEntity.ok().build();
    }
}