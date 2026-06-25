package com.dentalflow.pe.treatment.soap;

import com.dentalflow.pe.treatment.dto.TratamientoRequestDto;
import com.dentalflow.pe.treatment.dto.TratamientoResponseDto;
import com.dentalflow.pe.treatment.service.TratamientoService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@WebService(serviceName = "TratamientoService")
@Component
@RequiredArgsConstructor
public class TratamientoEndpoint {

    private final TratamientoService service;

    @WebMethod
    public String registrarTratamiento(
            @WebParam(name = "request") TratamientoRequestDto request
    ) {
        return service.registrarTratamiento(request);
    }

    @WebMethod
    public TratamientoResponseDto getTratamiento(
            @WebParam(name = "dniPaciente") String dniPaciente
    ) {
        return service.getTratamiento(dniPaciente);
    }

    @WebMethod
    public void actualizarEstado(
            @WebParam(name = "idTratamiento") int idTratamiento,
            @WebParam(name = "estado") String estado
    ) {
        service.actualizarEstado(idTratamiento, estado);
    }

    @WebMethod
    public String aumentarCostoTratamiento(
            @WebParam(name = "idTratamiento") int idTratamiento,
            @WebParam(name = "monto") BigDecimal monto
    ) {
        return service.aumentarCostoTratamiento(idTratamiento, monto);
    }
}