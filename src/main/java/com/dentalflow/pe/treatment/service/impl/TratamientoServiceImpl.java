package com.dentalflow.pe.treatment.service.impl;

import org.springframework.stereotype.Component;

import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;
import com.dentalflow.pe.clinicalStaff.repository.IClinicalStaffRepository;
import com.dentalflow.pe.patient.entity.Patient;
import com.dentalflow.pe.patient.repository.IPatientRepository;
import com.dentalflow.pe.payment.repository.IPagoRepository;
import com.dentalflow.pe.treatment.dto.TratamientoRequestDto;
import com.dentalflow.pe.treatment.dto.TratamientoResponseDto;
import com.dentalflow.pe.treatment.entity.Treatment;
import com.dentalflow.pe.treatment.entity.Treatment.EstadoTratamiento;
import com.dentalflow.pe.treatment.entity.TreatmentSession.EstadoSesion;
import com.dentalflow.pe.treatment.mapper.TratamientoMapper;
import com.dentalflow.pe.treatment.repository.ISesion_tratamientoRepository;
import com.dentalflow.pe.treatment.repository.ITratamientoRepository;
import com.dentalflow.pe.treatment.service.TratamientoService;

import jakarta.jws.WebService;
@Component
@WebService
public class TratamientoServiceImpl implements TratamientoService{

	private final ITratamientoRepository tratamientoRepository;
	private final IClinicalStaffRepository personalClinicoRepository;
	private final TratamientoMapper tratamientoMapper;
	private final IPatientRepository pacienteRepository;
	private final IPagoRepository pagoRepository;
	private final ISesion_tratamientoRepository sesion_tratamientoRepository;
	



	public TratamientoServiceImpl(ITratamientoRepository tratamientoRepository,
			IClinicalStaffRepository personalClinicoRepository, TratamientoMapper tratamientoMapper,
			IPatientRepository pacienteRepository, IPagoRepository pagoRepository,
			ISesion_tratamientoRepository sesion_tratamientoRepository) {
		this.tratamientoRepository = tratamientoRepository;
		this.personalClinicoRepository = personalClinicoRepository;
		this.tratamientoMapper = tratamientoMapper;
		this.pacienteRepository = pacienteRepository;
		this.pagoRepository = pagoRepository;
		this.sesion_tratamientoRepository = sesion_tratamientoRepository;
	}



	@Override
	public String registrarTratamiento(TratamientoRequestDto tratamientoRq) {
	
		Patient paciente = pacienteRepository.findById(tratamientoRq.getPacienteId()).orElseThrow(()-> new RuntimeException("Paciente no encontrado"));
		
		ClinicalStaff odontologo = personalClinicoRepository.findById(tratamientoRq.getOdontologoId()).orElseThrow(()-> new RuntimeException("Odontologo no encontrado"));
		
		if(!odontologo.getUsuario().getRol().getNombre().equalsIgnoreCase("ODONTOLOGO")) {
			throw new RuntimeException("El personal seleccionado no es un odontologo");
		}
		
		Treatment tratamiento = tratamientoMapper.toEntity(tratamientoRq);
		
		tratamiento.setOdontologo(odontologo);
		tratamiento.setPaciente(paciente);
		
		tratamientoRepository.save(tratamiento);
		
		return "tratamiento registrado correctamente";
	}



	@Override
	public TratamientoResponseDto getTratamiento(String dniPaciente) {
		if(!pacienteRepository.existsByDni(dniPaciente)) {
			throw new RuntimeException("Paciente no existe");
		}
		
		Treatment tratamiento = tratamientoRepository.findByPaciente_Dni(dniPaciente); 
		
		if(tratamiento.getEstado().equals(EstadoTratamiento.COMPLETADA)) {
			throw new RuntimeException("El tratamiento ya fue completado");
		}
		
		TratamientoResponseDto tratamientoDomain = tratamientoMapper.toResponse(tratamiento);
		
		
		tratamientoDomain.setSesionesRestantes(tratamiento.getCant_sesiones() - sesion_tratamientoRepository.countByTratamiento_IdAndEstado(tratamiento.getId(), EstadoSesion.REALIZADA));
		tratamientoDomain.setMontoPagado(pagoRepository.sumMontoByTratamiento_Id(tratamiento.getId()));
		
		return tratamientoDomain;
	}



	@Override
	public void actualizarEstado(int idTratamiento, String estado) {
		System.out.println("ID DE TRATAMIENTO:" + idTratamiento);
		System.out.println("ESTADO:" + estado);
		Treatment tratamientoEntity = tratamientoRepository.findById(idTratamiento).orElseThrow(()->new RuntimeException("tratamiento no encontrado"));
		
		tratamientoEntity.setEstado(EstadoTratamiento.valueOf(estado));
		
		tratamientoRepository.save(tratamientoEntity);
	}

}
