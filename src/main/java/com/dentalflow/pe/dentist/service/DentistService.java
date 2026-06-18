package com.dentalflow.pe.dentist.service;

import com.dentalflow.pe.dentist.entity.Dentist;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface DentistService {

    @WebMethod
    Dentist createDentist(
            Long userId,
            Long specialtyId,
            String licenseNumber,
            String firstName,
            String lastName,
            String phone
    );

    @WebMethod
    List<Dentist> getAllDentists();

    @WebMethod
    Dentist getDentistById(Long id);
}