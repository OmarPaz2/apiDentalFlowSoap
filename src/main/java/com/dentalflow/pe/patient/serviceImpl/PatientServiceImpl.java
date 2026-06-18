package com.dentalflow.pe.patient.serviceImpl;

import com.dentalflow.pe.patient.entity.Patient;
import com.dentalflow.pe.patient.repository.IPatientRepository;
import com.dentalflow.pe.patient.service.PatientService;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.patient.service.PatientService")
public class PatientServiceImpl implements PatientService {

    @Autowired
    private IPatientRepository repository;

    @Override
    public Patient createPatient(
            String dni,
            String firstName,
            String lastName,
            String birthDate,
            String gender,
            String phone,
            String email,
            String address
    ) {
        if (repository.findByDni(dni).isPresent()) {
            throw new RuntimeException("Patient already exists");
        }

        Patient patient = new Patient();
        patient.setDni(dni);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setBirthDate(LocalDate.parse(birthDate));
        patient.setGender(gender);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setAddress(address);
        patient.setCreatedAt(LocalDateTime.now());

        return repository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
}