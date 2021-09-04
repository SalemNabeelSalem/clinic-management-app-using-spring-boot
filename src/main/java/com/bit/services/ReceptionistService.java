package com.bit.services;

import com.bit.dtos.employee.CreateEmployeeDto;
import com.bit.dtos.employee.ShowEmployeeDto;
import com.bit.dtos.employee.UpdateEmployeeDto;
import com.bit.entities.Receptionist;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.ReceptionistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceptionistService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    public List<ShowEmployeeDto> findAllEmployees() {

        List<Receptionist> sortedEmployeesList = receptionistRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedEmployeesList.stream().map(
            obj -> modelMapper.map(obj, ShowEmployeeDto.class)
        ).collect(Collectors.toList());
    }

    public ShowEmployeeDto createNewEmployee(CreateEmployeeDto employeeRequest) {

        Receptionist receptionistData = new Receptionist();

        receptionistData.setFullName(employeeRequest.getFullName());

        receptionistData.setGender(employeeRequest.getGender());

        receptionistData.setPhone(employeeRequest.getPhone());

        receptionistData.setRole(employeeRequest.getRole());

        receptionistData.setUserName(employeeRequest.getUsername());

        receptionistData.setPassword(employeeRequest.getPassword());

        return modelMapper.map(
            receptionistRepository.save(receptionistData), ShowEmployeeDto.class
        );
    }

    public ShowEmployeeDto updateEmployee(Long employeeId, UpdateEmployeeDto employeeRequest) {

        if (receptionistRepository.findById(employeeId).isEmpty()) {

            throw new ResourceNotFoundException("employee with id: [" + employeeId + "] is not found.");
        }

        Receptionist receptionistData = receptionistRepository.findById(employeeId).get();

        receptionistData.setFullName(employeeRequest.getFullName());

        receptionistData.setGender(employeeRequest.getGender());

        receptionistData.setPhone(employeeRequest.getPhone());

        receptionistData.setRole(employeeRequest.getRole());

        receptionistData.setUserName(employeeRequest.getUserName());

        receptionistData.setPassword(employeeRequest.getPassword());

        return modelMapper.map(
            receptionistRepository.save(receptionistData), ShowEmployeeDto.class
        );
    }

    public ResponseEntity deleteEmployee(Long employeeId) {

        if (receptionistRepository.findById(employeeId).isEmpty()) {

            throw new ResourceNotFoundException("employee with id: [" + employeeId + "] is not found.");
        }

        Receptionist receptionistData = receptionistRepository.findById(employeeId).get();

        receptionistRepository.delete(receptionistData);

        return ResponseEntity.ok().build();
    }
}