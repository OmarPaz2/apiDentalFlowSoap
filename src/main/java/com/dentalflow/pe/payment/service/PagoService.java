package com.dentalflow.pe.payment.service;



import com.dentalflow.pe.payment.dto.PagoRequestDto;
import com.dentalflow.pe.payment.dto.PagoResponseDto;

import jakarta.jws.WebService;

@WebService
public interface PagoService {
PagoResponseDto registerPagoTratamiento(PagoRequestDto pago,int idTratamiento);
PagoResponseDto registerPagoCita(PagoRequestDto pago,int idCita);

PagoResponseDto findPagoById(int idPago);
}
