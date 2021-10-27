package com.bit.repositories;

import com.bit.entities.PatientCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientCheckRepository extends JpaRepository<PatientCheck, Long> {

    Optional<PatientCheck> findByReservationId(Long reservationId);

    List<PatientCheck> findAllByDoctorId(Long doctorId);
}