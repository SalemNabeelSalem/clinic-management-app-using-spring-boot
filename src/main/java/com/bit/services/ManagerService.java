package com.bit.services;

import com.bit.dtos.manager.CreateManagerDto;
import com.bit.dtos.manager.ShowManagerDto;
import com.bit.dtos.manager.UpdateManagerDto;
import com.bit.entities.Manager;
import com.bit.exceptions.ResourceNotFoundException;
import com.bit.repositories.ManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ManagerRepository managerRepository;

    public List<ShowManagerDto> findAllManagers() {

        List<Manager> sortedLaboratoriesList = managerRepository.findAll().stream().sorted(
            (o1, o2)->o2.getCreatedAt().compareTo(o1.getCreatedAt())
        ).collect(Collectors.toList());

        return sortedLaboratoriesList.stream().map(
            obj -> modelMapper.map(obj, ShowManagerDto.class)
        ).collect(Collectors.toList());
    }

    public ShowManagerDto createNewManager(CreateManagerDto managerInput) {

        Manager manager = new Manager();

        manager.setFullName(managerInput.getFullName());

        manager.setGender(managerInput.getGender());

        manager.setPhone(managerInput.getPhone());

        manager.setEmail(managerInput.getEmail());

        manager.setUserName(managerInput.getUserName());

        manager.setPassword(managerInput.getPassword());

        manager.setIsActive(true);

        return modelMapper.map(
            managerRepository.save(manager), ShowManagerDto.class
        );
    }

    public ShowManagerDto findManagerById(Long managerId) {
        return modelMapper.map(managerRepository.findById(managerId).orElseThrow(
            () -> new ResourceNotFoundException("manager with id: [" + managerId + "] is not found.")
        ), ShowManagerDto.class);
    }

    public ShowManagerDto updateManager(Long managerId, UpdateManagerDto managerInput) {

        Manager manager = managerRepository.findById(managerId).orElseThrow(
            () -> new ResourceNotFoundException("manager with id: [" + managerId + "] is not found.")
        );

        manager.setFullName(managerInput.getFullName());

        manager.setGender(managerInput.getGender());

        manager.setPhone(managerInput.getPhone());

        manager.setEmail(managerInput.getEmail());

        return modelMapper.map(managerRepository.save(manager), ShowManagerDto.class);
    }

    public ResponseEntity deactivateManager(Long managerId) {

        Manager manager = managerRepository.findById(managerId).orElseThrow(
            () -> new ResourceNotFoundException("manager with id: [" + managerId + "] is not found.")
        );

        manager.setIsActive(false);

        managerRepository.save(manager);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity activateManager(Long managerId) {

        Manager manager = managerRepository.findById(managerId).orElseThrow(
            () -> new ResourceNotFoundException("manager with id: [" + managerId + "] is not found.")
        );

        manager.setIsActive(true);

        managerRepository.save(manager);

        return ResponseEntity.ok().build();
    }
}