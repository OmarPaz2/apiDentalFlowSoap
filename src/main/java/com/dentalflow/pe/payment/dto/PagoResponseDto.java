package com.dentalflow.pe.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dentalflow.pe.utils.LocalDateTimeAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PagoResponseDto {
	 private int idPago;
	 private String razon;
	 private String nombresPaciente;
	 private String apellidosPaciente;
	 private String nombreEspecialidad;
	 private BigDecimal monto;
	 @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	 private LocalDateTime fecha;
	 private String metodoPago;
}
