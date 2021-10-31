package com.bit.controllers;

import com.bit.dtos.doctor.ShowDoctorDto;
import com.bit.dtos.laboratory.ShowLaboratoryDto;
import com.bit.dtos.manager.ShowManagerDto;
import com.bit.dtos.receptionist.ShowReceptionistDto;
import com.bit.models.LoginRequest;
import com.bit.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/recep-login")
    public ShowReceptionistDto receptionistLogin(@RequestBody LoginRequest login) {

        return loginService.receptionistLogin(login);
    }

    @PostMapping("/lab-login")
    public ShowLaboratoryDto labLogin(@RequestBody LoginRequest login) {

        return loginService.labLogin(login);
    }

    @PostMapping("/doctor-login")
    public ShowDoctorDto doctorLogin(@RequestBody LoginRequest login) {

        return loginService.doctorLogin(login);
    }

    @PostMapping("/manager-login")
    public ShowManagerDto managerLogin(@RequestBody LoginRequest login) {

        return loginService.managerLogin(login);
    }
}