package com.bit.services;

import com.bit.dtos.CreateEmployeeDto;
import com.bit.dtos.ShowEmployeeDto;
import com.bit.dtos.UpdateEmployeeDto;
import com.bit.entities.Employee;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<ShowEmployeeDto> findAllEmployees() {

        List<Employee> sortedEmployeesList = employeeRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        List<ShowEmployeeDto> showEmployeesDtoList = sortedEmployeesList.stream().map(
            obj -> modelMapper.map(obj, ShowEmployeeDto.class)
        ).collect(Collectors.toList());

        return showEmployeesDtoList;
    }

    public ShowEmployeeDto createNewEmployee(CreateEmployeeDto employeeRequest) {

        Employee employeeData = new Employee();

        employeeData.setFullName(employeeRequest.getFullName());
        employeeData.setGender(employeeRequest.getGender());
        employeeData.setEmail(employeeRequest.getEmail());
        employeeData.setPassword(employeeRequest.getPassword());
        employeeData.setPhone(employeeRequest.getPhone());
        employeeData.setImage(employeeRequest.getImage());
        employeeData.setRole(employeeRequest.getRole());
        employeeData.setIsActive(true);

        return modelMapper.map(
            employeeRepository.save(employeeData), ShowEmployeeDto.class
        );
    }

    public ShowEmployeeDto updateEmployee(Long employeeId, UpdateEmployeeDto employeeRequest) {

        if (employeeRepository.findById(employeeId).isEmpty()) {

            throw new ResourceNotFoundException("employee with id: [" + employeeId + "] is not found.");
        }

        Employee employeeData = employeeRepository.findById(employeeId).get();

        employeeData.setFullName(employeeRequest.getFullName());
        employeeData.setGender(employeeRequest.getGender());
        employeeData.setEmail(employeeRequest.getEmail());
        employeeData.setPhone(employeeRequest.getPhone());
        employeeData.setImage(employeeRequest.getImage());

        return modelMapper.map(
            employeeRepository.save(employeeData), ShowEmployeeDto.class
        );
    }
}