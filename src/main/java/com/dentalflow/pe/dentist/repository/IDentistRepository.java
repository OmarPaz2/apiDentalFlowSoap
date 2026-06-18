package com.dentalflow.pe.dentist.repository;

import com.dentalflow.pe.dentist.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDentistRepository
        extends JpaRepository<Dentist, Long> {

    Optional<Dentist> findByLicenseNumber(String licenseNumber);
}