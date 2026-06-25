package com.dentalflow.pe.clinicalStaff.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffDto;
import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffResponseDto;
import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;


@WebService
public interface ClinicalStaffService {

    @WebMethod
    ClinicalStaff createDentist(
            int userId,
            int specialtyId,
            String licenseNumber,
            String firstName,
            String lastName,
            String phone
    );

    @WebMethod
    List<ClinicalStaff> getAllDentistsBySpecialtyAndLastName(String lastName, int specialty);

    @WebMethod
    ClinicalStaff getDentistById(int id);
    
    ClinicalStaffResponseDto getByIdUser(int idUser);
    
    ClinicalStaffResponseDto updateDentist(int id,ClinicalStaffDto objeto);
}