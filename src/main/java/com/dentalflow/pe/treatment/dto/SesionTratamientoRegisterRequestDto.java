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
public class SesionTratamientoRegisterRequestDto {
	 private int idTratamiento;
	 @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	 private LocalDateTime fechaProgramada;
	 private BigDecimal costoParcial;
	 private int tiempoDuracion;
}
