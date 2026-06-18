package com.dentalflow.pe.dentist.serviceImpl;

import com.dentalflow.pe.dentist.entity.Dentist;
import com.dentalflow.pe.dentist.repository.IDentistRepository;
import com.dentalflow.pe.dentist.service.DentistService;
import com.dentalflow.pe.specialty.entity.Specialty;
import com.dentalflow.pe.specialty.repository.ISpecialtyRepository;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.dentist.service.DentistService")
public class DentistServiceImpl implements DentistService {

    @Autowired
    private IDentistRepository dentistRepository;

    @Autowired
    private ISpecialtyRepository specialtyRepository;

    @Override
    public Dentist createDentist(
            Long userId,
            Long specialtyId,
            String licenseNumber,
            String firstName,
            String lastName,
            String phone
    ) {
        if (dentistRepository.findByLicenseNumber(licenseNumber).isPresent()) {
            throw new RuntimeException("License number already exists");
        }

        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        Dentist dentist = new Dentist();
        dentist.setUserId(userId);
        dentist.setSpecialty(specialty);
        dentist.setLicenseNumber(licenseNumber);
        dentist.setFirstName(firstName);
        dentist.setLastName(lastName);
        dentist.setPhone(phone);
        dentist.setCreatedAt(LocalDateTime.now());

        return dentistRepository.save(dentist);
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist getDentistById(Long id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentist not found"));
    }
}