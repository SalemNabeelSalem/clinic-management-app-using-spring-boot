package com.bit.repositories;

import com.bit.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Integer countAllBy();

    Optional<Manager> findByEmailAndPassword(String email, String password);
}