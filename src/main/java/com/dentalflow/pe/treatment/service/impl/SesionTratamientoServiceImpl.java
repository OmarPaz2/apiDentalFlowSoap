package com.dentalflow.pe.treatment.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.dentalflow.pe.treatment.dto.SesionTratamientoRegisterRequestDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoResponseDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoUpdateRequestDto;
import com.dentalflow.pe.treatment.entity.Treatment;
import com.dentalflow.pe.treatment.entity.Treatment.EstadoTratamiento;
import com.dentalflow.pe.treatment.entity.TreatmentSession;
import com.dentalflow.pe.treatment.entity.TreatmentSession.EstadoSesion;
import com.dentalflow.pe.treatment.mapper.SesionMapper;
import com.dentalflow.pe.treatment.repository.ISesion_tratamientoRepository;
import com.dentalflow.pe.treatment.repository.ITratamientoRepository;
import com.dentalflow.pe.treatment.service.SesionTratamientoService;
import com.dentalflow.pe.treatment.service.TratamientoService;

import org.springframework.stereotype.Service;

@Service
public class SesionTratamientoServiceImpl implements SesionTratamientoService {

	private final ISesion_tratamientoRepository sesion_tratamientoRepository;
	private final ITratamientoRepository tratamientoRepository;
	private final SesionMapper sesionMapper;

	private final TratamientoService tratamientoService;

	public SesionTratamientoServiceImpl(ISesion_tratamientoRepository sesion_tratamientoRepository,
			ITratamientoRepository tratamientoRepository, SesionMapper sesionMapper,
			TratamientoService tratamientoService) {
		
		this.sesion_tratamientoRepository = sesion_tratamientoRepository;
		this.tratamientoRepository = tratamientoRepository;
		this.sesionMapper = sesionMapper;
		this.tratamientoService = tratamientoService;
	}

	@PreAuthorize("hasRole('ODONTOLOGO')")
	@Override
	public String registrarSesion(SesionTratamientoRegisterRequestDto sesion) {
		
		Treatment tratamiento = tratamientoRepository.findById(sesion.getIdTratamiento()).orElseThrow(() -> new RuntimeException("Tratamiento no existente o no enviado"));
		
		TreatmentSession sesionProgramadaEntity = sesion_tratamientoRepository.findByTratamiento_IdAndEstado(sesion.getIdTratamiento(), EstadoSesion.PROGRAMADA);
		
		if(sesionProgramadaEntity !=null) {
			throw new RuntimeException("No se puede crear una nueva sesion porque hay una sesion programada en curso");
		}
		//si el primer nmero es mayor que el otro, devuelve 1
		if(sesion.getCostoParcial().compareTo(tratamiento.getCostoEstimado()) >0) {
			throw new RuntimeException("El costo parcial no puede ser mayor al costo total del tratamiento");
		}
		
		//comprobar que el costo parcial ingresado no sea mayor que el monto que falta
		List<TreatmentSession> listSesiones = sesion_tratamientoRepository.findAllByTratamiento_Id(sesion.getIdTratamiento());
		
		BigDecimal totalCostoSesiones= BigDecimal.ZERO;
		int cantSesionesRealizadas = 0;
		
		for(TreatmentSession st : listSesiones) {
			if(st.getEstado().equals(EstadoSesion.REALIZADA)) {
				totalCostoSesiones = totalCostoSesiones.add(st.getCostoParcial());
				cantSesionesRealizadas++;
			}
		}
			
			BigDecimal restoApagarTratamiento = tratamiento.getCostoEstimado().subtract(totalCostoSesiones);
			
			if(sesion.getCostoParcial().compareTo(restoApagarTratamiento)>0) {
				throw new RuntimeException("el costo parcial no puede ser mayor al resto a pagar del tratamiento");
			}
			
			TreatmentSession sesionTratamiento = sesionMapper.toEntity(sesion);
			
			sesionTratamiento.setTratamiento(tratamiento);
			sesionTratamiento.setEstado(EstadoSesion.PROGRAMADA);
			
			
			int sesionesRestantes=tratamiento.getCant_sesiones() - cantSesionesRealizadas;
			
			if(sesionesRestantes ==0) {
				throw new RuntimeException("Ya se han llevado a cabo todas las sesiones establecidad");
			}
				//verificar que si falta una sesion hacer que el monto sea el restante de esa sesion
			if(sesionesRestantes == 1) {
				if(sesion.getCostoParcial().compareTo(restoApagarTratamiento) !=0) {
					sesionTratamiento.setCostoParcial(restoApagarTratamiento);
					sesion_tratamientoRepository.save(sesionTratamiento);
					return "Sesion registrada correctamente, pero el monto parcial fue modficado al faltar 1 sesion";
				}
			}
			sesion_tratamientoRepository.save(sesionTratamiento);
		return "Sesion registrada correctamente";
		
	}

	@PreAuthorize("hasRole('ODONTOLOGO')")
	@Override
	public String actualizarSesion(SesionTratamientoUpdateRequestDto sesion,int idSesion) {
		
		TreatmentSession sesionEntity = sesion_tratamientoRepository.findById(idSesion).orElseThrow(() -> new RuntimeException("sesion no encontrada"));
		
		sesionEntity.setEstado(EstadoSesion.REALIZADA);
		sesionEntity.setFechaRealizada(sesion.getFechaRealizada());
		sesionEntity.setObservaciones(sesion.getObservaciones());
		
		sesion_tratamientoRepository.save(sesionEntity);
		
		List<TreatmentSession> listSesiones = sesion_tratamientoRepository.findAllByTratamiento_Id(sesionEntity.getTratamiento().getId());
		int cantSesionesRealizadas=0;
		for(TreatmentSession st : listSesiones) {
			if(st.getEstado().equals(EstadoSesion.REALIZADA)) {
				cantSesionesRealizadas++;
			}
		}
		
		int sesionesRestantes = sesionEntity.getTratamiento().getCant_sesiones() - cantSesionesRealizadas;
		
		if(sesionesRestantes ==0) {
			tratamientoService.actualizarEstado(sesionEntity.getTratamiento().getId(), EstadoTratamiento.COMPLETADA.name());
		}
		
		return "datos de la seison registrada correctamente";
	}

	@PreAuthorize("hasRole('ODONTOLOGO')")
	@Override
	public SesionTratamientoResponseDto getSesion(int sesionId) {
		TreatmentSession sesionEntity =  sesion_tratamientoRepository.findById(sesionId).orElseThrow(()->new RuntimeException("sesion no enocntrada"));
		
		return sesionMapper.toResponse(sesionEntity);
	}

	@PreAuthorize("hasRole('ODONTOLOGO') or hasRole('RECEPCIONISTA')")
	@Override
	public String cancelarSesion(int idSesion) {
		TreatmentSession sesionEntity =  sesion_tratamientoRepository.findById(idSesion).orElseThrow(()->new RuntimeException("sesion no enocntrada"));
		
		sesionEntity.setEstado(EstadoSesion.CANCELADA);
		
		sesion_tratamientoRepository.save(sesionEntity);
		return "sesion cancelada correctamente";
	}

	@Override
	public List<SesionTratamientoResponseDto> getAllSesionesByIdTratamiento(int idTratamiento) {
		
		List<TreatmentSession> sesiones = sesion_tratamientoRepository.findAllByTratamiento_Id(idTratamiento);
		return convertirSesiones(sesiones);
	}

	@Override
	public List<SesionTratamientoResponseDto> sesionesParahoy() {
		LocalDate fechaHoy = LocalDate.now();
		
		List<TreatmentSession> sesiones =  sesion_tratamientoRepository.findAllByFechaProgramada(fechaHoy, EstadoSesion.PROGRAMADA);
		
		
		return convertirSesiones(sesiones);
		
	}
	
	private List<SesionTratamientoResponseDto> convertirSesiones(List<TreatmentSession> sesiones) {
		
		List<SesionTratamientoResponseDto> sesionesRp = new ArrayList<>();
		
		for(TreatmentSession sesion : sesiones) {
			sesionesRp.add(sesionMapper.toResponse(sesion));
		}
		
		return sesionesRp;
	}

}
