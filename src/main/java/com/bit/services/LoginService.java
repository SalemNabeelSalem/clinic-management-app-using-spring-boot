package com.bit.services;

import com.bit.dtos.doctor.ShowDoctorDto;
import com.bit.dtos.laboratory.ShowLaboratoryDto;
import com.bit.dtos.manager.ShowManagerDto;
import com.bit.dtos.receptionist.ShowReceptionistDto;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.models.LoginRequest;
import com.bit.repositories.DoctorRepository;
import com.bit.repositories.LaboratoryRepository;
import com.bit.repositories.ManagerRepository;
import com.bit.repositories.ReceptionistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public ShowReceptionistDto receptionistLogin(LoginRequest login) {
        return modelMapper.map(receptionistRepository.findByEmailAndPassword(login.getEmail(), login.getPassword()).orElseThrow(
            () -> new ResourceNotFoundException("user is not found.")
        ), ShowReceptionistDto.class);
    }

    public ShowLaboratoryDto labLogin(LoginRequest login) {
        return modelMapper.map(laboratoryRepository.findByEmailAndPassword(login.getEmail(), login.getPassword()).orElseThrow(
            () -> new ResourceNotFoundException("user is not found.")
        ), ShowLaboratoryDto.class);
    }

    public ShowDoctorDto doctorLogin(LoginRequest login) {
        return modelMapper.map(doctorRepository.findByEmailAndPassword(login.getEmail(), login.getPassword()).orElseThrow(
            () -> new ResourceNotFoundException("user is not found.")
        ), ShowDoctorDto.class);
    }

    public ShowManagerDto managerLogin(LoginRequest login) {
        return modelMapper.map(managerRepository.findByEmailAndPassword(login.getEmail(), login.getPassword()).orElseThrow(
            () -> new ResourceNotFoundException("user is not found.")
        ), ShowManagerDto.class);
    }
}