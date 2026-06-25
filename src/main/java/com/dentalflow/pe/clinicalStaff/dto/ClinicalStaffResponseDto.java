package com.dentalflow.pe.clinicalStaff.dto;

import lombok.Data;

@Data
public class ClinicalStaffResponseDto {
	private int id;
    private int specialty;

    private String licenseNumber;

    private String firstName;

    private String lastName;

    private String phone;
}
