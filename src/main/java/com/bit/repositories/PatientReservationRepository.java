package com.bit.repositories;

import com.bit.entities.PatientReservation;
import com.bit.entities.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PatientReservationRepository extends JpaRepository<PatientReservation, Long> {

    List<PatientReservation> findAllByReceptionist(Receptionist receptionist);

    @Query(value = "SELECT (SELECT COUNT(*) FROM patients_reservations) AS Total,\n" +
        "(SELECT COUNT(*) FROM patients_reservations WHERE gender = 'MALE') AS Male,\n" +
        "(SELECT COUNT(*) FROM patients_reservations WHERE gender = 'Female') AS Female", nativeQuery = true)
    Optional<Map<String, Object[]>> getPatientsReservationsChartData();
}