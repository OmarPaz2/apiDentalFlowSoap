package com.dentalflow.pe.appointment.repository;

import com.dentalflow.pe.appointment.entity.Appointment;
import com.dentalflow.pe.appointment.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDentistIdAndAppointmentDateAndStatusNot(
            Long dentistId,
            LocalDate appointmentDate,
            AppointmentStatus status
    );

    List<Appointment> findByDentistIdAndAppointmentDateAndStatusNotAndIdNot(
            Long dentistId,
            LocalDate appointmentDate,
            AppointmentStatus status,
            Long id
    );

    List<Appointment> findByDentistId(Long dentistId);

    List<Appointment> findByPatientId(Long patientId);
}