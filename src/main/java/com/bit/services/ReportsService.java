package com.bit.services;

import com.bit.dtos.reports.UsersChartData;
import com.bit.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportsService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PatientReservationRepository patientReservationRepository;

    public UsersChartData getUsersChartData() {

        UsersChartData usersChartData = new UsersChartData();

        usersChartData.setDoctors(doctorRepository.countAllBy());

        usersChartData.setReceptionists(receptionistRepository.countAllBy());

        usersChartData.setLaboratories(laboratoryRepository.countAllBy());

        usersChartData.setManagers(managerRepository.countAllBy());

        return usersChartData;
    }

    public Map<String, Object[]> getPatientsReservationsChartData() {

        Map<String, Object[]> patientsReservationsChartData = patientReservationRepository
                .getPatientsReservationsChartData().orElse(new HashMap<>());

        return patientsReservationsChartData;
    }
}