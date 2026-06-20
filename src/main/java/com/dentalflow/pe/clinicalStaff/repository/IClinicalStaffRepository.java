package com.dentalflow.pe.clinicalStaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;


import java.util.Optional;

public interface IClinicalStaffRepository
        extends JpaRepository<ClinicalStaff, Integer> {

    Optional<ClinicalStaff> findByLicenseNumber(String licenseNumber);
}