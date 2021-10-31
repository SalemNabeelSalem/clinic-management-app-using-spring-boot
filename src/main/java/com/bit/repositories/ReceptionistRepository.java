package com.bit.repositories;

import com.bit.entities.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

    Integer countAllBy();

    Optional<Receptionist> findByEmailAndPassword(String email, String password);
}