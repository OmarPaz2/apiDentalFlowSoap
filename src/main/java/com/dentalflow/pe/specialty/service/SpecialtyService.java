package com.dentalflow.pe.specialty.service;

import com.dentalflow.pe.specialty.entity.Specialty;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface SpecialtyService {

    @WebMethod
    Specialty createSpecialty(String name);

    @WebMethod
    Specialty updateSpecialty(int id, String name);

    @WebMethod
    List<Specialty> getAllSpecialties();
}