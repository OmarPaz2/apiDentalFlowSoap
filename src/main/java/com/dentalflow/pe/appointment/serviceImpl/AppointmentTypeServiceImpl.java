package com.dentalflow.pe.appointment.serviceImpl;

import com.dentalflow.pe.appointment.entity.AppointmentType;
import com.dentalflow.pe.appointment.repository.IAppointmentTypeRepository;
import com.dentalflow.pe.appointment.service.AppointmentTypeService;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.appointment.service.AppointmentTypeService")
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private IAppointmentTypeRepository repository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
    @Override
    public AppointmentType createAppointmentType(
            String name,
            Integer durationMinutes,
            BigDecimal price
    ) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException(
                    "Appointment type name is required"
            );
        }

        if (durationMinutes == null || durationMinutes <= 0) {
            throw new RuntimeException(
                    "Duration must be greater than zero"
            );
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Price must be greater than zero"
            );
        }
        repository.findByName(name.trim())
                .ifPresent(existing -> {
                    throw new RuntimeException(
                            "Appointment type already exists"
                    );
                });

        AppointmentType type = new AppointmentType();
        type.setName(name.trim());
        type.setDurationMinutes(durationMinutes);
        type.setPrice(price);
        type.setCreatedAt(LocalDateTime.now());

        return repository.save(type);
    }

    @PreAuthorize(
            "hasRole('ADMIN') or " +
                    "hasRole('RECEPCIONISTA') or " +
                    "hasRole('ODONTOLOGO')"
    )
    @Override
    public List<AppointmentType> getAllAppointmentTypes() {
        return repository.findAll();
    }

	@Override
	public String updateType(Long id,AppointmentType type) {
		AppointmentType typeEntity = repository.findById(id).orElseThrow(()->new RuntimeException("Error al encotrar el tipo"));
		
		typeEntity.setName(type.getName());
		typeEntity.setDurationMinutes(type.getDurationMinutes());
		
		try {
			repository.save(typeEntity);
			return "Tipo actualizado correctamente";
		}catch (Exception e) {
			throw new RuntimeException("Error al guardar la actualizacion");
		}
	}

	@Override
	public AppointmentType findType(Long id) {
			return repository.findById(id).orElseThrow(()->new RuntimeException("Tipo de cita no encontrado"));
		
	}
}