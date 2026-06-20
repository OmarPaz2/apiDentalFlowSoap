package com.dentalflow.pe.treatment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dentalflow.pe.treatment.dto.TratamientoRequestDto;
import com.dentalflow.pe.treatment.dto.TratamientoResponseDto;
import com.dentalflow.pe.treatment.entity.Treatment;

@Mapper(componentModel = "spring")
public interface TratamientoMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "odontologo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Treatment toEntity(TratamientoRequestDto dto);
    

    @Mapping(target = "estado",source = "estado",qualifiedByName = "estadoToString")
    @Mapping(target = "idTratamiento",source = "id")
    @Mapping(target = "nombresPaciente",source = "paciente.firstName")
    @Mapping(target = "apellidosPaciente",source = "paciente.lastName")
    @Mapping(target = "dni",source = "paciente.dni")
    @Mapping(target = "nombresOdontologo",source = "odontologo.firstName")
    @Mapping(target = "apellidosOdontologo",source = "odontologo.lastName")
    @Mapping(target = "sesionesRestantes",ignore = true)
    @Mapping(target = "montoPagado",ignore = true)
    TratamientoResponseDto toResponse(Treatment tratamiento);

    
    @Named("estadoToString")
    default String estadoToString(
    		Treatment.EstadoTratamiento estado) {

        return estado == null 
                ? null 
                : estado.name();
    }

}