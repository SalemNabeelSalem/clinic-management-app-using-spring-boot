package com.bit.services;

import com.bit.dtos.receptionist.CreateReceptionistDto;
import com.bit.dtos.receptionist.ShowReceptionistDto;
import com.bit.dtos.receptionist.UpdateReceptionistDto;
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

    public List<ShowReceptionistDto> findAllReceptionists() {

        List<Receptionist> sortedEmployeesList = receptionistRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedEmployeesList.stream().map(
            obj -> modelMapper.map(obj, ShowReceptionistDto.class)
        ).collect(Collectors.toList());
    }

    public ShowReceptionistDto createNewReceptionist(CreateReceptionistDto receptionistInput) {

        Receptionist receptionist = new Receptionist();

        receptionist.setFullName(receptionistInput.getFullName());

        receptionist.setGender(receptionistInput.getGender());

        receptionist.setPhone(receptionistInput.getPhone());

        receptionist.setEmail(receptionistInput.getEmail());

        receptionist.setUserName(receptionistInput.getUserName());

        receptionist.setPassword(receptionistInput.getPassword());

        receptionist.setIsActive(true);

        return modelMapper.map(
            receptionistRepository.save(receptionist), ShowReceptionistDto.class
        );
    }

    public ShowReceptionistDto findReceptionistById(Long receptionistId) {

        return modelMapper.map(receptionistRepository.findById(receptionistId).orElseThrow(
                () -> new ResourceNotFoundException("receptionist with id: [" + receptionistId + "] is not found.")
            ), ShowReceptionistDto.class
        );
    }

    public ShowReceptionistDto updateReceptionist(Long receptionistId, UpdateReceptionistDto receptionistInput) {

        if (receptionistRepository.findById(receptionistId).isEmpty()) {

            throw new ResourceNotFoundException("receptionist with id: [" + receptionistId + "] is not found.");
        }

        Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

        receptionist.setFullName(receptionistInput.getFullName());

        receptionist.setGender(receptionistInput.getGender());

        receptionist.setPhone(receptionistInput.getPhone());

        receptionist.setEmail(receptionistInput.getEmail());

        return modelMapper.map(
            receptionistRepository.save(receptionist), ShowReceptionistDto.class
        );
    }

    public ResponseEntity deactivateReceptionist(Long receptionistId) {

        if (receptionistRepository.findById(receptionistId).isEmpty()) {

            throw new ResourceNotFoundException("receptionist with id: [" + receptionistId + "] is not found.");
        }

        Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

        receptionist.setIsActive(false);

        receptionistRepository.save(receptionist);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity activateReceptionist(Long receptionistId) {

        if (receptionistRepository.findById(receptionistId).isEmpty()) {

            throw new ResourceNotFoundException("receptionist with id: [" + receptionistId + "] is not found.");
        }

        Receptionist receptionist = receptionistRepository.findById(receptionistId).get();

        receptionist.setIsActive(true);

        receptionistRepository.save(receptionist);

        return ResponseEntity.ok().build();
    }
}