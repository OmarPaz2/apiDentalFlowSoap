package com.dentalflow.pe.appointment.soap;

import com.dentalflow.pe.appointment.entity.Appointment;
import com.dentalflow.pe.appointment.serviceImpl.AppointmentServiceImpl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@WebService(serviceName = "AppointmentService")
@Component
@RequiredArgsConstructor
public class AppointmentEndpoint {

    private final AppointmentServiceImpl appointmentService;

    @WebMethod
    public Appointment createAppointment(
            @WebParam(name = "patientId") int patientId,
            @WebParam(name = "dentistId") int dentistId,
            @WebParam(name = "appointmentTypeId") Long appointmentTypeId,
            @WebParam(name = "date") String date,
            @WebParam(name = "startTime") String startTime,
            @WebParam(name = "reason") String reason
    ) {
        return appointmentService.createAppointment(
                patientId,
                dentistId,
                appointmentTypeId,
                date,
                startTime,
                reason
        );
    }

    @WebMethod
    public Appointment rescheduleAppointment(
            @WebParam(name = "appointmentId") int appointmentId,
            @WebParam(name = "newDate") String newDate,
            @WebParam(name = "newStartTime") String newStartTime
    ) {
        return appointmentService.rescheduleAppointment(
                appointmentId,
                newDate,
                newStartTime
        );
    }

    @WebMethod
    public Appointment cancelAppointment(
            @WebParam(name = "appointmentId") int appointmentId
    ) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @WebMethod
    public Appointment getAppointmentById(
            @WebParam(name = "id") int id
    ) {
        return appointmentService.getAppointmentById(id);
    }

    @WebMethod
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @WebMethod
    public List<Appointment> getAppointmentsByDentist(
            @WebParam(name = "dentistId") int dentistId
    ) {
        return appointmentService.getAppointmentsByDentist(dentistId);
    }

    @WebMethod
    public List<Appointment> getAppointmentsByPatient(
            @WebParam(name = "patientId") int patientId
    ) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }
}