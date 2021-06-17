package com.bit.controllers;

import com.bit.dtos.employee.CreateEmployeeDto;
import com.bit.dtos.employee.ShowEmployeeDto;
import com.bit.dtos.employee.UpdateEmployeeDto;
import com.bit.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<ShowEmployeeDto> findAllEmployees() {

        return employeeService.findAllEmployees();
    }

    @PostMapping("/employees")
    public ShowEmployeeDto createNewEmployee(@RequestBody CreateEmployeeDto employeeRequest) {

        return employeeService.createNewEmployee(employeeRequest);
    }

    @PutMapping("/employees/{id}")
    public ShowEmployeeDto updateEmployee(@PathVariable("id") Long employeeId,
                                          @RequestBody UpdateEmployeeDto employeeRequest) {

        return employeeService.updateEmployee(employeeId, employeeRequest);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long employeeId) {

        return employeeService.deleteEmployee(employeeId);
    }
}