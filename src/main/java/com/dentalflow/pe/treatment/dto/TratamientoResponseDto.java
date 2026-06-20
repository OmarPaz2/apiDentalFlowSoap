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
public class TratamientoResponseDto {
	private Integer idTratamiento;
	private String nombresPaciente;
	private String apellidosPaciente;
	private String dni;
	private String nombresOdontologo;
	private String apellidosOdontologo;
	private String diagnostico;
	private String tipoTratamiento;
	private BigDecimal costoEstimado;
	 @XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate fechaInicio;
	 private int cant_sesiones;
	 private int sesionesRestantes;
	 private BigDecimal montoPagado;
	 private String estado;
}
