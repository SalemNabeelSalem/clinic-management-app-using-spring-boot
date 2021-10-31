package com.bit.repositories;

import com.bit.entities.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

    Integer countAllBy();

    Optional<Laboratory> findByEmailAndPassword(String email, String password);
}