package com.bit.controllers;

import com.bit.dtos.manager.CreateManagerDto;
import com.bit.dtos.manager.ShowManagerDto;
import com.bit.dtos.manager.UpdateManagerDto;
import com.bit.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/managers")
    public List<ShowManagerDto> findAllManagers() {

        return managerService.findAllManagers();
    }

    @PostMapping("/managers")
    public ShowManagerDto createNewManager(@RequestBody CreateManagerDto managerRequest) {

        return managerService.createNewManager(managerRequest);
    }

    @GetMapping("/managers/{id}")
    public ShowManagerDto findManagerById(@PathVariable("id") Long managerId) {

        return managerService.findManagerById(managerId);
    }

    @PutMapping("/managers/{id}")
    public ShowManagerDto updateManager(@PathVariable("id") Long managerId,
                                        @RequestBody UpdateManagerDto managerRequest) {

        return managerService.updateManager(managerId, managerRequest);
    }

    @DeleteMapping("/managers/{id}/deactivate")
    public ResponseEntity deactivateManager(@PathVariable("id") Long managerId) {

        return managerService.deactivateManager(managerId);
    }

    @PutMapping("/managers/{id}/activate")
    public ResponseEntity activateManager(@PathVariable("id") Long managerId) {

        return managerService.activateManager(managerId);
    }
}