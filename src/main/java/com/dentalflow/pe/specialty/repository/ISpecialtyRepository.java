package com.dentalflow.pe.specialty.repository;

import com.dentalflow.pe.specialty.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpecialtyRepository
        extends JpaRepository<Specialty, Integer> {

    Optional<Specialty> findByName(String name);
}