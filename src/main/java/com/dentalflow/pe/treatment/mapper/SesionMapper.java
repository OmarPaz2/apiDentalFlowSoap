package com.dentalflow.pe.treatment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dentalflow.pe.treatment.dto.SesionTratamientoRegisterRequestDto;
import com.dentalflow.pe.treatment.dto.SesionTratamientoResponseDto;
import com.dentalflow.pe.treatment.entity.TreatmentSession;



@Mapper(componentModel = "spring")
public interface SesionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tratamiento", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaRealizada", ignore = true)
    @Mapping(target = "observaciones", ignore = true)
    TreatmentSession toEntity(SesionTratamientoRegisterRequestDto dto);
    

    @Mapping(target = "estado",source = "estado",qualifiedByName = "estadoToString")
    @Mapping(target = "idSesion",source = "id")
    @Mapping(target = "nombrePaciente",source = "tratamiento.paciente.firstName")
    @Mapping(target = "apellidoPaciente",source = "tratamiento.paciente.lastName")
    @Mapping(target = "dni",source = "tratamiento.paciente.dni")
    SesionTratamientoResponseDto toResponse(TreatmentSession sesion);

    
    @Named("estadoToString")
    default String estadoToString(
    		TreatmentSession.EstadoSesion estado) {

        return estado == null 
                ? null 
                : estado.name();
    }
}
