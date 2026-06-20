package com.dentalflow.pe.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dentalflow.pe.payment.dto.PagoRequestDto;
import com.dentalflow.pe.payment.dto.PagoResponseDto;
import com.dentalflow.pe.payment.entity.Pago;
import com.dentalflow.pe.payment.entity.Pago.MetodoPago;





@Mapper(componentModel = "spring")
public interface PagoMapper {

	    @Mapping(target = "id", ignore = true)
	    @Mapping(target = "tratamiento", ignore = true)
	    @Mapping(target = "cita", ignore = true)
	    @Mapping(target = "metodoPago",source = "metodoPago",qualifiedByName = "stringToMetodo")
	    @Mapping(target = "fecha", ignore = true)
	    Pago toEntity(PagoRequestDto dto);
	    
	    
	    @Mapping(target = "metodoPago",source = "metodoPago",qualifiedByName = "metodoToString")
	    @Mapping(target = "idPago",source = "id")
	    @Mapping(target = "nombresPaciente",source = "tratamiento.paciente.firstName")
	    @Mapping(target = "apellidosPaciente",source = "tratamiento.paciente.lastName")
	    @Mapping(target = "nombreEspecialidad",source = "tratamiento.odontologo.specialty.name")
	    @Mapping(target = "monto",source = "monto")
	    @Mapping(target = "fecha",source = "fecha")
	    @Mapping(target = "razon",ignore=true)
	    PagoResponseDto toResponsePagoTratamiento(Pago pago);
	    
	    @Mapping(target = "metodoPago",source = "metodoPago",qualifiedByName = "metodoToString")
	    @Mapping(target = "idPago",source = "id")
	    @Mapping(target = "nombresPaciente",source = "cita.paciente.firstName")
	    @Mapping(target = "apellidosPaciente",source = "cita.paciente.lastName")
	    @Mapping(target = "nombreEspecialidad",source = "cita.odontologo.specialty.name")
	    @Mapping(target = "monto",source = "monto")
	    @Mapping(target = "fecha",source = "fecha")
	    @Mapping(target = "razon",ignore=true)
	    PagoResponseDto toResponsePagoCita(Pago pago);
	    
	    @Named("metodoToString")
	    default String metodoToString(
	            Pago.MetodoPago metodo) {

	        return metodo == null 
	                ? null 
	                : metodo.name();
	    }
	    
	    @Named("stringToMetodo")
	    default MetodoPago stringToMetodo(
	            String metodo) {

	        return metodo == null 
	                ? null 
	                : MetodoPago.valueOf(metodo);
	    }
}
