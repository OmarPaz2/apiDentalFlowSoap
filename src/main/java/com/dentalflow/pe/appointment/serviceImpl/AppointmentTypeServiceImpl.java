package com.dentalflow.pe.appointment.serviceImpl;

import com.dentalflow.pe.appointment.entity.AppointmentType;
import com.dentalflow.pe.appointment.repository.IAppointmentTypeRepository;
import com.dentalflow.pe.appointment.service.AppointmentTypeService;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.appointment.service.AppointmentTypeService")
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private IAppointmentTypeRepository repository;

    @Override
    public AppointmentType createAppointmentType(
            String name,
            Integer durationMinutes
    ) {
        repository.findByName(name).ifPresent(existing -> {
            throw new RuntimeException("Appointment type already exists");
        });

        AppointmentType type = new AppointmentType();
        type.setName(name);
        type.setDurationMinutes(durationMinutes);
        type.setCreatedAt(LocalDateTime.now());

        return repository.save(type);
    }

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