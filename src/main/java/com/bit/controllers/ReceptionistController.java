package com.bit.controllers;

import com.bit.dtos.receptionist.CreateReceptionistDto;
import com.bit.dtos.receptionist.ShowReceptionistDto;
import com.bit.dtos.receptionist.UpdateReceptionistDto;
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

    @GetMapping("/receptionists")
    public List<ShowReceptionistDto> findAllReceptionists() {

        return receptionistService.findAllReceptionists();
    }

    @PostMapping("/receptionists")
    public ShowReceptionistDto createNewReceptionist(@RequestBody CreateReceptionistDto receptionistRequest) {

        return receptionistService.createNewReceptionist(receptionistRequest);
    }

    @PutMapping("/receptionists/{id}")
    public ShowReceptionistDto updateReceptionist(@PathVariable("id") Long receptionistId,
                                                  @RequestBody UpdateReceptionistDto receptionistRequest) {

        return receptionistService.updateReceptionist(receptionistId, receptionistRequest);
    }

    @DeleteMapping("/receptionists/{id}/deactivate")
    public ResponseEntity deactivateReceptionist(@PathVariable("id") Long receptionistId) {

        return receptionistService.deactivateReceptionist(receptionistId);
    }

    @PutMapping("/receptionists/{id}/activate")
    public ResponseEntity activateReceptionist(@PathVariable("id") Long receptionistId) {

        return receptionistService.activateReceptionist(receptionistId);
    }
}