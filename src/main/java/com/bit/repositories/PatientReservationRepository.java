package com.bit.repositories;

import com.bit.entities.PatientReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientReservationRepository extends JpaRepository<PatientReservation, Long> {

    @Query(value = "SELECT * FROM patients_reservations WHERE is_active = 1", nativeQuery = true)
    List<PatientReservation> findAll();
}