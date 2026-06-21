package com.dentalflow.pe.clinicalStaff.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

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
    List<ClinicalStaff> getAllDentists();

    @WebMethod
    ClinicalStaff getDentistById(int id);
}