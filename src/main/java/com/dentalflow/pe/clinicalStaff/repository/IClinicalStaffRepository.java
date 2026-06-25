package com.dentalflow.pe.clinicalStaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;

import java.util.List;
import java.util.Optional;

public interface IClinicalStaffRepository
        extends JpaRepository<ClinicalStaff, Integer> {

    Optional<ClinicalStaff> findByLicenseNumber(String licenseNumber);
    
    @Query("SELECT c FROM ClinicalStaff c WHERE (:lastName IS NULL OR :lastName = '' OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND c.specialty.id = :id")
    List<ClinicalStaff> findAllByLastNameAndSpecialty_Id(String lastName, int id);
    
    ClinicalStaff findByUsuario_Id(int id);
}