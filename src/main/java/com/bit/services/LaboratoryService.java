package com.bit.services;

import com.bit.dtos.laboratory.CreateLaboratoryDto;
import com.bit.dtos.laboratory.ShowLaboratoryDto;
import com.bit.dtos.laboratory.UpdateLaboratoryDto;
import com.bit.entities.Laboratory;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.LaboratoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaboratoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public List<ShowLaboratoryDto> findAllLaboratories() {

        List<Laboratory> sortedLaboratoriesList = laboratoryRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedLaboratoriesList.stream().map(
            obj -> modelMapper.map(obj, ShowLaboratoryDto.class)
        ).collect(Collectors.toList());
    }

    public ShowLaboratoryDto createNewLaboratory(CreateLaboratoryDto laboratoryInput) {

        Laboratory laboratory = new Laboratory();

        laboratory.setFullName(laboratoryInput.getFullName());

        laboratory.setGender(laboratoryInput.getGender());

        laboratory.setPhone(laboratoryInput.getPhone());

        laboratory.setEmail(laboratoryInput.getEmail());

        laboratory.setUserName(laboratoryInput.getUserName());

        laboratory.setPassword(laboratoryInput.getPassword());

        laboratory.setIsActive(true);

        return modelMapper.map(
            laboratoryRepository.save(laboratory), ShowLaboratoryDto.class
        );
    }

    public ShowLaboratoryDto findLaboratoryById(Long laboratoryId) {
        return modelMapper.map(laboratoryRepository.findById(laboratoryId).orElseThrow(
            () -> new ResourceNotFoundException("laboratory with id: [" + laboratoryId + "] is not found.")
        ), ShowLaboratoryDto.class);
    }

    public ShowLaboratoryDto updateLaboratory(Long laboratoryId, UpdateLaboratoryDto laboratoryInput) {

        Laboratory laboratory = laboratoryRepository.findById(laboratoryId).orElseThrow(
            () -> new ResourceNotFoundException("laboratory with id: [" + laboratoryId + "] is not found.")
        );

        laboratory.setFullName(laboratoryInput.getFullName());

        laboratory.setGender(laboratoryInput.getGender());

        laboratory.setPhone(laboratoryInput.getPhone());

        laboratory.setEmail(laboratoryInput.getEmail());

        return modelMapper.map(laboratoryRepository.save(laboratory), ShowLaboratoryDto.class);
    }

    public ResponseEntity deactivateLaboratory(Long laboratoryId) {

        Laboratory laboratory = laboratoryRepository.findById(laboratoryId).orElseThrow(
            () -> new ResourceNotFoundException("laboratory with id: [" + laboratoryId + "] is not found.")
        );

        laboratory.setIsActive(false);

        laboratoryRepository.save(laboratory);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity activateLaboratory(Long laboratoryId) {

        Laboratory laboratory = laboratoryRepository.findById(laboratoryId).orElseThrow(
            () -> new ResourceNotFoundException("laboratory with id: [" + laboratoryId + "] is not found.")
        );

        laboratory.setIsActive(true);

        laboratoryRepository.save(laboratory);

        return ResponseEntity.ok().build();
    }
}