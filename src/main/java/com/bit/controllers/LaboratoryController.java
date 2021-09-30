package com.bit.controllers;

import com.bit.dtos.laboratory.CreateLaboratoryDto;
import com.bit.dtos.laboratory.ShowLaboratoryDto;
import com.bit.dtos.laboratory.UpdateLaboratoryDto;
import com.bit.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/laboratories")
    public List<ShowLaboratoryDto> findAllLaboratories() {

        return laboratoryService.findAllLaboratories();
    }

    @PostMapping("/laboratories")
    public ShowLaboratoryDto createNewLaboratory(@RequestBody CreateLaboratoryDto laboratoryRequest) {

        return laboratoryService.createNewLaboratory(laboratoryRequest);
    }

    @GetMapping("/laboratories/{id}")
    public ShowLaboratoryDto findLaboratoryById(@PathVariable("id") Long laboratoryId) {

        return laboratoryService.findLaboratoryById(laboratoryId);
    }

    @PutMapping("/laboratories/{id}")
    public ShowLaboratoryDto updateLaboratory(@PathVariable("id") Long laboratoryId,
                                              @RequestBody UpdateLaboratoryDto laboratoryRequest) {

        return laboratoryService.updateLaboratory(laboratoryId, laboratoryRequest);
    }

    @DeleteMapping("/laboratories/{id}/deactivate")
    public ResponseEntity deactivateLaboratory(@PathVariable("id") Long laboratoryId) {

        return laboratoryService.deactivateLaboratory(laboratoryId);
    }

    @PutMapping("/laboratories/{id}/activate")
    public ResponseEntity activateLaboratory(@PathVariable("id") Long laboratoryId) {

        return laboratoryService.activateLaboratory(laboratoryId);
    }
}