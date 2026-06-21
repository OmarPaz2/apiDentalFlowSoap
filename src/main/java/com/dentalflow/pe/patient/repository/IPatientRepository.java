package com.dentalflow.pe.patient.repository;

import com.dentalflow.pe.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByDni(String dni);
    
    boolean existsByDni(String dni);
    
    @Query("""
            SELECT p FROM Patient p WHERE
            (:dni IS NULL OR p.dni = :dni OR p.dni LIKE CONCAT(:dni, '%')) AND
            (:firstName IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND
            (:lastName IS NULL OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
            """)
    List<Patient> search(String dni,String firstName,String lastName);
}