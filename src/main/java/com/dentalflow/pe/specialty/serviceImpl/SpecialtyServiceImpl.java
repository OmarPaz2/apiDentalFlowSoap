package com.dentalflow.pe.specialty.serviceImpl;

import com.dentalflow.pe.specialty.entity.Specialty;
import com.dentalflow.pe.specialty.repository.ISpecialtyRepository;
import com.dentalflow.pe.specialty.service.SpecialtyService;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.specialty.service.SpecialtyService")
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private ISpecialtyRepository repository;

    @Override
    public Specialty createSpecialty(String name) {
        if (repository.findByName(name).isPresent()) {
            throw new RuntimeException("Specialty already exists");
        }

        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialty.setCreatedAt(LocalDateTime.now());

        return repository.save(specialty);
    }

    @Override
    public Specialty updateSpecialty(Long id, String name) {
        Specialty specialty = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        repository.findByName(name).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new RuntimeException("Specialty name already exists");
            }
        });

        specialty.setName(name);

        return repository.save(specialty);
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        return repository.findAll();
    }
}