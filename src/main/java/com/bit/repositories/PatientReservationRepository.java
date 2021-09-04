package com.bit.repositories;

import com.bit.entities.PatientReservation;
import com.bit.entities.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientReservationRepository extends JpaRepository<PatientReservation, Long> {

    List<PatientReservation> findAllByReceptionist(Receptionist receptionist);
}