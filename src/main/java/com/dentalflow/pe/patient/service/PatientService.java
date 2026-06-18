package com.dentalflow.pe.patient.service;

import com.dentalflow.pe.patient.entity.Patient;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PatientService {

    @WebMethod
    Patient createPatient(
            String dni,
            String firstName,
            String lastName,
            String birthDate,
            String gender,
            String phone,
            String email,
            String address
    );

    @WebMethod
    Patient getPatientById(Long id);

    @WebMethod
    List<Patient> getAllPatients();
}