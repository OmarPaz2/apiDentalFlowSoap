package com.dentalflow.pe.treatment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sesiones_tratamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Treatment tratamiento;

    @Column(name = "fecha_programada", nullable = true)
    private LocalDateTime fechaProgramada;


    @Column(name = "fecha_realizada",nullable = true)
    private LocalDateTime fechaRealizada;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "costo_parcial", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoParcial = BigDecimal.ZERO;
    
    @Column(name="tiempoEjecucion", nullable=false)
    private LocalTime tiempoEjecucion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSesion estado = EstadoSesion.PROGRAMADA;


    public enum EstadoSesion {

        PROGRAMADA,
        REALIZADA,
        CANCELADA
    }
}
