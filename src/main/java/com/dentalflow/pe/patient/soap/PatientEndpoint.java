package com.dentalflow.pe.patient.soap;

import com.dentalflow.pe.patient.dto.PatientRequestDto;
import com.dentalflow.pe.patient.dto.PatientResponseDto;
import com.dentalflow.pe.patient.serviceImpl.PatientServiceImpl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@WebService(serviceName = "PatientService")
@Component
@RequiredArgsConstructor
public class PatientEndpoint {

    private final PatientServiceImpl patientService;

    @WebMethod
    public PatientResponseDto createPatient(
            @WebParam(name = "dni") String dni,
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName,
            @WebParam(name = "birthDate") String birthDate,
            @WebParam(name = "gender") String gender,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "email") String email,
            @WebParam(name = "address") String address
    ) {
        PatientRequestDto patient = new PatientRequestDto();

        patient.setDni(dni);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(LocalDate.parse(birthDate));
        patient.setGender(gender);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setAddress(address);

        return patientService.createPatient(patient);
    }

    @WebMethod
    public PatientResponseDto getPatientById(
            @WebParam(name = "id") int id
    ) {
        return patientService.getPatientById(id);
    }

    @WebMethod
    public List<PatientResponseDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @WebMethod
    public String deletePatient(
            @WebParam(name = "id") int id
    ) {
        return patientService.deletePatient(id);
    }

    @WebMethod
    public PatientResponseDto updatePatient(
            @WebParam(name = "id") int id,
            @WebParam(name = "dni") String dni,
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName,
            @WebParam(name = "birthDate") String birthDate,
            @WebParam(name = "gender") String gender,
            @WebParam(name = "phone") String phone,
            @WebParam(name = "email") String email,
            @WebParam(name = "address") String address
    ) {
        PatientRequestDto patient = new PatientRequestDto();

        patient.setDni(dni);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(LocalDate.parse(birthDate));
        patient.setGender(gender);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setAddress(address);

        return patientService.updatePatient(patient, id);
    }

    @WebMethod
    public List<PatientResponseDto> searchPatient(
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido
    ) {
        return patientService.searchPatient(
                dni,
                nombre,
                apellido
        );
    }
}