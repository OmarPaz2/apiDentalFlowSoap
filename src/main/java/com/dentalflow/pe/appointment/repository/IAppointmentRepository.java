package com.dentalflow.pe.appointment.repository;

import com.dentalflow.pe.appointment.entity.Appointment;
import com.dentalflow.pe.appointment.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByDentistIdAndAppointmentDateAndStatusNot(
            int dentistId,
            LocalDate appointmentDate,
            AppointmentStatus status
    );

    List<Appointment> findByDentistIdAndAppointmentDateAndStatusNotAndIdNot(
            int dentistId,
            LocalDate appointmentDate,
            AppointmentStatus status,
            int id
    );

    List<Appointment> findByDentistId(int dentistId);

    List<Appointment> findByPatientId(int patientId);
}