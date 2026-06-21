package com.dentalflow.pe.treatment.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dentalflow.pe.clinicalStaff.entity.ClinicalStaff;
import com.dentalflow.pe.patient.entity.Patient;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tratamientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "paciente_id", nullable = false)
    private Patient paciente;
    
    @ManyToOne()
    @JoinColumn(name = "odontologo_id", nullable = false)
    private ClinicalStaff odontologo;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String diagnostico;

    @Column(name = "tipo_treatment", nullable = false, length = 100)
    private String tipoTratamiento;

    @Column(name = "costo_estimado", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoEstimado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    
    @Column(name = "pagado", nullable = false)
    private boolean pagado;

    private int cant_sesiones;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTratamiento estado = EstadoTratamiento.PLANIFICADO;

    public enum EstadoTratamiento {
        PLANIFICADO, EN_PROGRESO, COMPLETADA, INTERRUMPIDO
    }
}
