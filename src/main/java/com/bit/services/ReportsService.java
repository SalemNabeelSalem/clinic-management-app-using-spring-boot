package com.bit.services;

import com.bit.dtos.reports.UsersChartData;
import com.bit.repositories.DoctorRepository;
import com.bit.repositories.LaboratoryRepository;
import com.bit.repositories.ManagerRepository;
import com.bit.repositories.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UsersChartData getUsersChartData() {

        UsersChartData usersChartData = new UsersChartData();

        usersChartData.setDoctors(doctorRepository.countAllBy());

        usersChartData.setReceptionists(receptionistRepository.countAllBy());

        usersChartData.setLaboratories(laboratoryRepository.countAllBy());

        usersChartData.setManagers(managerRepository.countAllBy());

        return usersChartData;
    }
}