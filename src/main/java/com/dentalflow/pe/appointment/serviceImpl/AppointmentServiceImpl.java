package com.dentalflow.pe.appointment.serviceImpl;

import com.dentalflow.pe.appointment.entity.*;
import com.dentalflow.pe.appointment.repository.*;
import com.dentalflow.pe.appointment.service.AppointmentService;
import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;
import com.dentalflow.pe.clinicalStaff.repository.IClinicalStaffRepository;
import com.dentalflow.pe.patient.entity.Patient;
import com.dentalflow.pe.patient.repository.IPatientRepository;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.appointment.service.AppointmentService")
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IClinicalStaffRepository dentistRepository;

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IAppointmentTypeRepository appointmentTypeRepository;

    @Override
    public Appointment createAppointment(
            int patientId,
            int dentistId,
            Long appointmentTypeId,
            String date,
            String startTime,
            String reason
    ) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        ClinicalStaff dentist = dentistRepository.findById(dentistId)
                .orElseThrow(() -> new RuntimeException("Dentist not found"));

        AppointmentType type = appointmentTypeRepository.findById(appointmentTypeId)
                .orElseThrow(() -> new RuntimeException("Appointment type not found"));

        LocalDate appointmentDate = LocalDate.parse(date);
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = start.plusMinutes(type.getDurationMinutes());

        validateOverlap(dentistId, appointmentDate, start, end);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setAppointmentType(type);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStartTime(start);
        appointment.setEndTime(end);
        appointment.setReason(reason);
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment rescheduleAppointment(
            int appointmentId,
            String newDate,
            String newStartTime
    ) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new RuntimeException("Cannot reschedule cancelled appointment");
        }

        LocalDate date = LocalDate.parse(newDate);
        LocalTime start = LocalTime.parse(newStartTime);
        LocalTime end = start.plusMinutes(
                appointment.getAppointmentType().getDurationMinutes()
        );

//        validateOverlap(
//                appointment.getDentist().getId(),
//                date,
//                start,
//                end
//        );

        validateOverlapForReschedule(
                appointment.getDentist().getId(),
                date,
                start,
                end,
                appointment.getId()
        );


        appointment.setAppointmentDate(date);
        appointment.setStartTime(start);
        appointment.setEndTime(end);
        appointment.setUpdatedAt(LocalDateTime.now());

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(int appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.setUpdatedAt(LocalDateTime.now());

        return appointmentRepository.save(appointment);
    }

    private void validateOverlap(
            int dentistId,
            LocalDate date,
            LocalTime start,
            LocalTime end
    ) {
        List<Appointment> appointments =
                appointmentRepository.findByDentistIdAndAppointmentDateAndStatusNot(
                        dentistId,
                        date,
                        AppointmentStatus.CANCELLED
                );

        for (Appointment existing : appointments) {
            boolean overlap =
                    start.isBefore(existing.getEndTime()) &&
                            end.isAfter(existing.getStartTime());

            if (overlap) {
                throw new RuntimeException(
                        "Schedule conflict for dentist"
                );
            }
        }
    }

    private void validateOverlapForReschedule(
            int dentistId,
            LocalDate date,
            LocalTime start,
            LocalTime end,
            int appointmentId
    ) {
        List<Appointment> appointments =
                appointmentRepository.findByDentistIdAndAppointmentDateAndStatusNotAndIdNot(
                        dentistId,
                        date,
                        AppointmentStatus.CANCELLED,
                        appointmentId
                );

        for (Appointment existing : appointments) {
            boolean overlap =
                    start.isBefore(existing.getEndTime()) &&
                            end.isAfter(existing.getStartTime());

            if (overlap) {
                throw new RuntimeException("Schedule conflict for dentist");
            }
        }
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByDentist(int dentistId) {
        return appointmentRepository.findByDentistId(dentistId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

}