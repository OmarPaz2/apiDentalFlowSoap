package com.dentalflow.pe.treatment.soap;

import com.dentalflow.pe.treatment.dto.SesionTratamientoRegisterRequestDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoResponseDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoUpdateRequestDto;
import com.dentalflow.pe.treatment.service.SesionTratamientoService;
import com.dentalflow.pe.utils.LocalDateAdapter;
import com.dentalflow.pe.utils.LocalTimeAdapter;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebService(serviceName = "SesionTratamientoService")
@Component
@RequiredArgsConstructor
public class SesionTratamientoEndpoint {

    private final SesionTratamientoService service;

    @WebMethod
    public String registrarSesion(
            @WebParam(name = "request") SesionTratamientoRegisterRequestDto request
    ) {
        return service.registrarSesion(request);
    }

    @WebMethod
    public String actualizarSesion(
            @WebParam(name = "idSesion") int idSesion,
            @WebParam(name = "request") SesionTratamientoUpdateRequestDto request
    ) {
        return service.actualizarSesion(request, idSesion);
    }

    @WebMethod
    public SesionTratamientoResponseDto getSesion(
            @WebParam(name = "idSesion") int idSesion
    ) {
        return service.getSesion(idSesion);
    }

    @WebMethod
    public String cancelarSesion(
            @WebParam(name = "idSesion") int idSesion
    ) {
        return service.cancelarSesion(idSesion);
    }

    @WebMethod
    public List<SesionTratamientoResponseDto> getAllSesionesByIdTratamiento(
            @WebParam(name = "idTratamiento") int idTratamiento
    ) {
        return service.getAllSesionesByIdTratamiento(idTratamiento);
    }

    @WebMethod
    public List<SesionTratamientoResponseDto> sesionesParahoy(@WebParam(name = "idOdontologo")int idOdontologo, Boolean asistencia) {
        return service.sesionesParahoy(idOdontologo,asistencia);
    }
    @WebMethod
    public LocalTime fechaRecomendada(int idOdontologo, @XmlJavaTypeAdapter(LocalTimeAdapter.class)LocalTime  horaSolicitada,@XmlJavaTypeAdapter(LocalDateAdapter.class)LocalDate fecha) {
    	return service.fechaRecomendada(idOdontologo, horaSolicitada, fecha);
    }
    @WebMethod
	public String marcarAsistenciaPaciente(int idSesion) {
    	return service.marcarAsistenciaPaciente(idSesion);
    }
}