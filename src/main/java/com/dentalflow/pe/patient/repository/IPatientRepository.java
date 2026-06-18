package com.dentalflow.pe.patient.repository;

import com.dentalflow.pe.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(String dni);
}