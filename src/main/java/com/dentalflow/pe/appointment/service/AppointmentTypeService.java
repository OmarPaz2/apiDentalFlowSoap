package com.dentalflow.pe.appointment.service;

import com.dentalflow.pe.appointment.entity.AppointmentType;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.math.BigDecimal;
import java.util.List;

@WebService
public interface AppointmentTypeService {

    @WebMethod
    AppointmentType createAppointmentType(
            String name,
            Integer durationMinutes,
            BigDecimal price
    );

    @WebMethod
    List<AppointmentType> getAllAppointmentTypes();
    
    String updateType(Long id,AppointmentType type);
    
    AppointmentType findType(Long id);
}