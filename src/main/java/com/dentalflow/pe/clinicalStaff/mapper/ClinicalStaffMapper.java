package com.dentalflow.pe.clinicalStaff.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffDto;
import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffResponseDto;
import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;


@Mapper(componentModel="spring")
public interface ClinicalStaffMapper {
	    @Mapping(target = "id", ignore = true)
	    @Mapping(target = "createdAt", ignore = true)
	    @Mapping(target = "usuario", ignore = true)
	    @Mapping(target = "specialty", ignore = true)
	    @Mapping(target = "disponible", ignore = true)
	    ClinicalStaff toEntity(ClinicalStaffDto objeto);
	    
	    
	    @Mapping(target = "specialty", source="specialty.id")	   
	    ClinicalStaffResponseDto toDomain(ClinicalStaff patient);
}
