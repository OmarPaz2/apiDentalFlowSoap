package com.dentalflow.pe.patient.serviceImpl;

import com.dentalflow.pe.patient.dto.PatientRequestDto;
import com.dentalflow.pe.patient.dto.PatientResponseDto;
import com.dentalflow.pe.patient.entity.Patient;
import com.dentalflow.pe.patient.mapper.PatientMapper;
import com.dentalflow.pe.patient.repository.IPatientRepository;
import com.dentalflow.pe.patient.service.PatientService;

import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@WebService(endpointInterface = "com.dentalflow.pe.patient.service.PatientService")
public class PatientServiceImpl implements PatientService {

    @Autowired
    private IPatientRepository repository;
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patient) {
        if (repository.findByDni(patient.getDni()).isPresent()) {
            throw new RuntimeException("Patient already exists");
        }

        Patient patientEntity = patientMapper.toEntity(patient);

        return patientMapper.toDomain(repository.save(patientEntity));
    }

    @Override
    public PatientResponseDto getPatientById(int id) {
        return patientMapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found")));
    }

    @Override
    public List<PatientResponseDto> getAllPatients() { 	
    	return convertPatients(repository.findAll());
        
    }

	@Override
	public String deletePatient(int id) {
		
		Patient patient = repository.findById(id).orElseThrow(()->new RuntimeException("Patient not found"));
		String patientDNI=patient.getDni();
		try {
			repository.delete(patient);
		}catch(Exception ex){
			throw new RuntimeException("It was not possible to delete the patient record");
		}
		
		return "Paciente con id:" + id + " y con DNI: " + patientDNI + " eliminado con exito";
	}

	@Override
	public PatientResponseDto updatePatient(PatientRequestDto patient, int id) {
		Patient patientEntity = repository.findById(id).orElseThrow(()->new RuntimeException("Patient not found"));
		
		patientEntity.setAddress(patient.getAddress());
		patientEntity.setBirthDate(patient.getBirthDate());
		patientEntity.setDni(patient.getDni());
		patientEntity.setEmail(patient.getEmail());
		patientEntity.setFirstName(patient.getFirstName());
		patientEntity.setLastName(patient.getLastName());
		patientEntity.setGender(patient.getGender());
		patientEntity.setPhone(patient.getPhone());
		return patientMapper.toDomain(repository.save(patientEntity));
	}

	@Override
	public List<PatientResponseDto> searchPatient(String dni, String nombre, String apellido) {
		 String normalizedDni  = normalizar(dni);
	        String normalizedFirstName  = normalizar(nombre);
	        String normalizedLastName  = normalizar(apellido);

	        if (normalizedDni  == null && normalizedFirstName  == null && normalizedLastName  == null) {
	            throw new RuntimeException("Valor de busqueda invalido");
	        }

	        List<Patient> patients = repository.search(normalizedDni,normalizedFirstName,normalizedLastName);

	       return convertPatients(patients);
	}
	
	//metodos auxiliares
	
	 private String normalizar(String valor) {
	        if (valor == null || valor.isBlank()) {
	            return null;
	        }
	        return valor.trim();
	    }
	 
	 private List<PatientResponseDto> convertPatients(List<Patient> listPatients){
		 List<PatientResponseDto> patientRp = new ArrayList<PatientResponseDto>();
	    	
	        for(Patient patient : listPatients) {
	        	patientRp.add(patientMapper.toDomain(patient));
	        }
	        return patientRp;
	 }
}