package com.bit.controllers;

import com.bit.dtos.employee.CreateEmployeeDto;
import com.bit.dtos.employee.ShowEmployeeDto;
import com.bit.dtos.employee.UpdateEmployeeDto;
import com.bit.services.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReceptionistController {

    @Autowired
    private ReceptionistService receptionistService;

    @GetMapping("/employees")
    public List<ShowEmployeeDto> findAllEmployees() {

        return receptionistService.findAllEmployees();
    }

    @PostMapping("/employees")
    public ShowEmployeeDto createNewEmployee(@RequestBody CreateEmployeeDto employeeRequest) {

        return receptionistService.createNewEmployee(employeeRequest);
    }

    @PutMapping("/employees/{id}")
    public ShowEmployeeDto updateEmployee(@PathVariable("id") Long employeeId,
                                          @RequestBody UpdateEmployeeDto employeeRequest) {

        return receptionistService.updateEmployee(employeeId, employeeRequest);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Long employeeId) {

        return receptionistService.deleteEmployee(employeeId);
    }
}