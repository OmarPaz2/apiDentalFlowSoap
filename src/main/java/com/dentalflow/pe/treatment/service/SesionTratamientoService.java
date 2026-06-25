package com.dentalflow.pe.treatment.service;


import java.time.LocalDate;

import java.time.LocalTime;
import java.util.List;

import com.dentalflow.pe.treatment.dto.SesionTratamientoRegisterRequestDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoResponseDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoUpdateRequestDto;
import com.dentalflow.pe.utils.LocalDateAdapter;
import com.dentalflow.pe.utils.LocalTimeAdapter;

import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface SesionTratamientoService {

	String registrarSesion(SesionTratamientoRegisterRequestDto sesion);
	String actualizarSesion(SesionTratamientoUpdateRequestDto sesion,int idSesion);
	SesionTratamientoResponseDto getSesion(int sesionId);
	String cancelarSesion(int idSesion);
	List<SesionTratamientoResponseDto> getAllSesionesByIdTratamiento(int idTratamiento); 
	List<SesionTratamientoResponseDto> sesionesParahoy(int idOdontologo,Boolean asistencia);
	LocalTime fechaRecomendada(int idOdontologo, @XmlJavaTypeAdapter(LocalTimeAdapter.class)LocalTime  horaSolicitada,@XmlJavaTypeAdapter(LocalDateAdapter.class)LocalDate fecha);
	String marcarAsistenciaPaciente(int idSesion);
}
