package com.dentalflow.pe.appointment.service;

import com.dentalflow.pe.appointment.entity.AppointmentType;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface AppointmentTypeService {

    @WebMethod
    AppointmentType createAppointmentType(
            String name,
            Integer durationMinutes
    );

    @WebMethod
    List<AppointmentType> getAllAppointmentTypes();
    
    String updateType(Long id,AppointmentType type);
    
    AppointmentType findType(Long id);
}