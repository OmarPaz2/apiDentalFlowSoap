package com.dentalflow.pe.payment.soap;

import com.dentalflow.pe.payment.dto.PagoRequestDto;
import com.dentalflow.pe.payment.dto.PagoResponseDto;
import com.dentalflow.pe.payment.service.PagoService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@WebService(serviceName = "PagoService")
@Component
@RequiredArgsConstructor
public class PagoEndpoint {

    private final PagoService pagoService;

    @WebMethod
    public PagoResponseDto registerPagoTratamiento(
            @WebParam(name = "pago") PagoRequestDto pago,
            @WebParam(name = "idTratamiento") int idTratamiento
    ) {
        return pagoService.registerPagoTratamiento(pago, idTratamiento);
    }

    @WebMethod
    public PagoResponseDto registerPagoCita(
            @WebParam(name = "pago") PagoRequestDto pago,
            @WebParam(name = "idCita") int idCita
    ) {
        return pagoService.registerPagoCita(pago, idCita);
    }

    @WebMethod
    public PagoResponseDto findPagoById(
            @WebParam(name = "idPago") int idPago
    ) {
        return pagoService.findPagoById(idPago);
    }
}