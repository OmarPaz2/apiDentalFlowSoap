package com.dentalflow.pe.clinicalStaff.dto;

import lombok.Data;

@Data
public class ClinicalStaffDto {

    private int usuario;
    private int specialty;

    private String licenseNumber;

    private String firstName;

    private String lastName;

    private String phone;
}
