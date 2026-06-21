package com.dentalflow.pe.patient.service;

import com.dentalflow.pe.patient.dto.PatientRequestDto;
import com.dentalflow.pe.patient.dto.PatientResponseDto;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PatientService {

    @WebMethod
    PatientResponseDto createPatient(PatientRequestDto patient);

    @WebMethod
    PatientResponseDto getPatientById(int id);

    @WebMethod
    List<PatientResponseDto> getAllPatients();
    
    String deletePatient(int id);
    PatientResponseDto updatePatient(PatientRequestDto patient, int id);
    List<PatientResponseDto> searchPatient(String dni,String nombre,String apellido);  
}