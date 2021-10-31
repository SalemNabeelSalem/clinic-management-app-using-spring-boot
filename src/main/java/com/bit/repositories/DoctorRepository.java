package com.bit.repositories;

import com.bit.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Integer countAllBy();

    Optional<Doctor> findByEmailAndPassword(String email, String password);
}