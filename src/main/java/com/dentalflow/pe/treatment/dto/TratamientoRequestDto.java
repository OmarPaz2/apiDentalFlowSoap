package com.dentalflow.pe.treatment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dentalflow.pe.utils.LocalDateAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TratamientoRequestDto {

	private int pacienteId;
	private int odontologoId;
	private String diagnostico;
	private String tipoTratamiento;
	private BigDecimal costoEstimado;
	 @XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate fechaInicio;
	 private int cant_sesiones;
	
}
