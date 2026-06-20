package com.dentalflow.pe.appointment.service;

import com.dentalflow.pe.appointment.entity.Appointment;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface AppointmentService {

    @WebMethod
    Appointment createAppointment(
            int patientId,
            int dentistId,
            Long appointmentTypeId,
            String date,
            String startTime,
            String reason
    );

    @WebMethod
    Appointment rescheduleAppointment(
            int appointmentId,
            String newDate,
            String newStartTime
    );

    @WebMethod
    Appointment cancelAppointment(int appointmentId);

    @WebMethod
    Appointment getAppointmentById(int id);

    @WebMethod
    List<Appointment> getAllAppointments();

    @WebMethod
    List<Appointment> getAppointmentsByDentist(int dentistId);

    @WebMethod
    List<Appointment> getAppointmentsByPatient(int patientId);
}