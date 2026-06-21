package com.dentalflow.pe.treatment.service;


import java.util.List;

import com.dentalflow.pe.treatment.dto.SesionTratamientoRegisterRequestDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoResponseDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoUpdateRequestDto;

import jakarta.jws.WebService;

@WebService
public interface SesionTratamientoService {

	String registrarSesion(SesionTratamientoRegisterRequestDto sesion);
	String actualizarSesion(SesionTratamientoUpdateRequestDto sesion,int idSesion);
	SesionTratamientoResponseDto getSesion(int sesionId);
	String cancelarSesion(int idSesion);
	List<SesionTratamientoResponseDto> getAllSesionesByIdTratamiento(int idTratamiento);
	List<SesionTratamientoResponseDto> sesionesParahoy();
	
}
