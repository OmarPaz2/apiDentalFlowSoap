package com.dentalflow.pe.payment.dto;

import java.math.BigDecimal;



import lombok.Data;

@Data
public class PagoRequestDto {
	 private BigDecimal monto;
	 private String metodoPago;
}
