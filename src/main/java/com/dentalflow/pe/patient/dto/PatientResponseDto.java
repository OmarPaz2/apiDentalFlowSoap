package com.dentalflow.pe.patient.dto;

import java.time.LocalDate;

import com.dentalflow.pe.utils.LocalDateAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientResponseDto {
    private int id;

    private String dni;

    private String firstName;

    private String lastName;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    private String gender;
 
    private String phone;

    private String email;

    private String address;
}
