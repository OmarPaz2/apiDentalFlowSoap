package com.dentalflow.pe.treatment.dto;

import java.time.LocalDateTime;

import com.dentalflow.pe.utils.LocalDateTimeAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SesionTratamientoUpdateRequestDto {
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	 private LocalDateTime fechaRealizada;
	 private String observaciones;
}
