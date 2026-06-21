package com.dentalflow.pe.patient.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dentalflow.pe.patient.dto.PatientRequestDto;
import com.dentalflow.pe.patient.dto.PatientResponseDto;
import com.dentalflow.pe.patient.entity.Patient;


@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Patient toEntity(PatientRequestDto patient);
    
    
    
    PatientResponseDto toDomain(Patient patient);
}
