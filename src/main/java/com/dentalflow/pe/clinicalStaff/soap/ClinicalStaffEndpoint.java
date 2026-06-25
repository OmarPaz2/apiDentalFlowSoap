package com.dentalflow.pe.clinicalStaff.soap;

import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffDto;
import com.dentalflow.pe.clinicalStaff.dto.ClinicalStaffResponseDto;
import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;
import com.dentalflow.pe.clinicalStaff.service.ClinicalStaffService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@WebService(serviceName = "ClinicalStaffService")
@Component
@RequiredArgsConstructor
public class ClinicalStaffEndpoint {

    private final ClinicalStaffService clinicalStaffService;

    @WebMethod
    public ClinicalStaff createDentist(
            @WebParam(name = "userId") int userId,
            @WebParam(name = "specialtyId") int specialtyId,
            @WebParam(name = "licenseNumber") String licenseNumber,
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName,
            @WebParam(name = "phone") String phone
    ) {
        return clinicalStaffService.createDentist(
                userId, specialtyId, licenseNumber,
                firstName, lastName, phone
        );
    }

    @WebMethod
    public List<ClinicalStaff> getAllDentistsBySpecialtyAndLastName(String lastName, int specialty) {
        return clinicalStaffService.getAllDentistsBySpecialtyAndLastName(lastName,specialty);
    }

    @WebMethod
    public ClinicalStaff getDentistById(
            @WebParam(name = "id") int id
    ) {
        return clinicalStaffService.getDentistById(id);
    }
    @WebMethod
    public ClinicalStaffResponseDto getByIdUser(int idUser) {
    	return clinicalStaffService.getByIdUser(idUser);
    }
    @WebMethod
    public ClinicalStaffResponseDto updateDentist(int id,ClinicalStaffDto objeto) {
    	return clinicalStaffService.updateDentist(id, objeto);
    }
}