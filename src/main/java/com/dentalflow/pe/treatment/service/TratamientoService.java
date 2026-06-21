package com.dentalflow.pe.treatment.service;



import java.math.BigDecimal;

import com.dentalflow.pe.treatment.dto.TratamientoRequestDto;
import com.dentalflow.pe.treatment.dto.TratamientoResponseDto;

import jakarta.jws.WebService;

@WebService
public interface TratamientoService {

	String registrarTratamiento(TratamientoRequestDto tratamientoRq);
	
	TratamientoResponseDto getTratamiento(String dniPaciente);
	
	void actualizarEstado(int idTratamiento,String estado);
	String aumentarCostoTratamiento(int idTratamiento, BigDecimal monto);
}
