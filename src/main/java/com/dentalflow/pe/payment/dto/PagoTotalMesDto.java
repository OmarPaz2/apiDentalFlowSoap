package com.dentalflow.pe.payment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagoTotalMesDto {
private int anio;
private int mes;
private BigDecimal totalPago;
}
