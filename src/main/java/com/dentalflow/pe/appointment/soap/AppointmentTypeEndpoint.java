package com.dentalflow.pe.appointment.soap;

import com.dentalflow.pe.appointment.entity.AppointmentType;
import com.dentalflow.pe.appointment.serviceImpl.AppointmentTypeServiceImpl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@WebService(serviceName = "AppointmentTypeService")
@Component
@RequiredArgsConstructor
public class AppointmentTypeEndpoint {

    private final AppointmentTypeServiceImpl appointmentTypeService;

    @WebMethod
    public AppointmentType createAppointmentType(
            @WebParam(name = "name") String name,
            @WebParam(name = "durationMinutes") Integer durationMinutes,
            @WebParam(name = "price") BigDecimal price
    ) {
        return appointmentTypeService.createAppointmentType(
                name,
                durationMinutes,
                price
        );
    }

    @WebMethod
    public List<AppointmentType> getAllAppointmentTypes() {
        return appointmentTypeService.getAllAppointmentTypes();
    }
}