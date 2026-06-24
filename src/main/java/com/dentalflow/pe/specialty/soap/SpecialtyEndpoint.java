package com.dentalflow.pe.specialty.soap;

import com.dentalflow.pe.specialty.entity.Specialty;
import com.dentalflow.pe.specialty.service.SpecialtyService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@WebService(serviceName = "SpecialtyService")
@Component
@RequiredArgsConstructor
public class SpecialtyEndpoint {

    private final SpecialtyService specialtyService;

    @WebMethod
    public Specialty createSpecialty(
            @WebParam(name = "name") String name
    ) {
        return specialtyService.createSpecialty(name);
    }

    @WebMethod
    public Specialty updateSpecialty(
            @WebParam(name = "id") int id,
            @WebParam(name = "name") String name
    ) {
        return specialtyService.updateSpecialty(id, name);
    }

    @WebMethod
    public List<Specialty> getAllSpecialties() {
        return specialtyService.getAllSpecialties();
    }
}