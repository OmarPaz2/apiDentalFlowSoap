package com.dentalflow.pe.clinicalStaff.serviceImpl;

import com.dentalflow.pe.auth.entity.Usuario;
import com.dentalflow.pe.auth.repository.IUsuarioRepository;
import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;
import com.dentalflow.pe.clinicalStaff.repository.IClinicalStaffRepository;
import com.dentalflow.pe.clinicalStaff.service.ClinicalStaffService;
import com.dentalflow.pe.specialty.entity.Specialty;
import com.dentalflow.pe.specialty.repository.ISpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicalStaffServiceImpl implements ClinicalStaffService {

    private final IClinicalStaffRepository clinicalStaffRepository;
    private final ISpecialtyRepository specialtyRepository;
    private final IUsuarioRepository usuarioRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ClinicalStaff createDentist(
            int userId,
            int specialtyId,
            String licenseNumber,
            String firstName,
            String lastName,
            String phone
    ) {
        if (clinicalStaffRepository.findByLicenseNumber(licenseNumber).isPresent()) {
            throw new RuntimeException("License number already exists");
        }

        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ClinicalStaff dentist = new ClinicalStaff();
        dentist.setUsuario(user);
        dentist.setSpecialty(specialty);
        dentist.setLicenseNumber(licenseNumber);
        dentist.setFirstName(firstName);
        dentist.setLastName(lastName);
        dentist.setPhone(phone);
        dentist.setCreatedAt(LocalDateTime.now());

        return clinicalStaffRepository.save(dentist);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
    @Override
    public List<ClinicalStaff> getAllDentists() {
        return clinicalStaffRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
    @Override
    public ClinicalStaff getDentistById(int id) {
        return clinicalStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentist not found"));
    }
}