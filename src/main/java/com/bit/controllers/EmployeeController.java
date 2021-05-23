package com.bit.controllers;

import com.bit.dtos.CreateEmployeeDto;
import com.bit.dtos.ShowEmployeeDto;
import com.bit.dtos.UpdateEmployeeDto;
import com.bit.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<ShowEmployeeDto> findAllEmployees() {

        return employeeService.findAllEmployees();
    }

    @PostMapping("/employee")
    public ShowEmployeeDto createNewEmployee(@RequestBody CreateEmployeeDto employeeRequest) {

        return employeeService.createNewEmployee(employeeRequest);
    }

    @PutMapping("/employee/{id}")
    public ShowEmployeeDto updateEmployee(@PathVariable("id") Long employeeId,
                                          @RequestBody UpdateEmployeeDto employeeRequest) {

        return employeeService.updateEmployee(employeeId, employeeRequest);
    }
}