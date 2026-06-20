package com.dentalflow.pe.clinicalStaff.entity;

import com.dentalflow.pe.auth.entity.Usuario;
import com.dentalflow.pe.specialty.entity.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "personal_clinico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClinicalStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@ManyToOne(optional = false)
    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "especialidad_id")
    private Specialty specialty;

    @Column(name="numero_colegiatura", nullable = true, unique = true,length = 20)
    private String licenseNumber;

    @Column(name="nombres", nullable = false)
    private String firstName;

    @Column(name="apellidos", nullable = false)
    private String lastName;

    @Column(name="telefono",length = 9)
    private String phone;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_personal", nullable = false)
    private TipoPersonal tipoPersonal;

    @Column(nullable = false)
    private Boolean disponible = true;

    public enum TipoPersonal {
        ADMINISTRADOR, RECEPCIONISTA, ODONTOLOGO
    }
    
   
}