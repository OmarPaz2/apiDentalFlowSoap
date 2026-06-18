package com.dentalflow.pe.appointment.service;

import com.dentalflow.pe.appointment.entity.Appointment;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface AppointmentService {

    @WebMethod
    Appointment createAppointment(
            Long patientId,
            Long dentistId,
            Long appointmentTypeId,
            String date,
            String startTime,
            String reason
    );

    @WebMethod
    Appointment rescheduleAppointment(
            Long appointmentId,
            String newDate,
            String newStartTime
    );

    @WebMethod
    Appointment cancelAppointment(Long appointmentId);

    @WebMethod
    Appointment getAppointmentById(Long id);

    @WebMethod
    List<Appointment> getAllAppointments();

    @WebMethod
    List<Appointment> getAppointmentsByDentist(Long dentistId);

    @WebMethod
    List<Appointment> getAppointmentsByPatient(Long patientId);
}