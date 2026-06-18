package com.dentalflow.pe.appointment.repository;

import com.dentalflow.pe.appointment.entity.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {

    Optional<AppointmentType> findByName(String name);
}