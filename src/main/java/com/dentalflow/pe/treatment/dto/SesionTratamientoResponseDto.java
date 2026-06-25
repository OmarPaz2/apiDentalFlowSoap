package com.dentalflow.pe.treatment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dentalflow.pe.utils.LocalDateTimeAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SesionTratamientoResponseDto {

	private int idSesion;
	private String nombrePaciente;
	private String apellidoPaciente;
	private String dni;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	 private LocalDateTime fechaProgramada;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	 private LocalDateTime fechaRealizada;
	 private BigDecimal costoParcial;
	 private String observaciones;
	 private String estado;
	 private boolean asistenciaPaciente;
}
