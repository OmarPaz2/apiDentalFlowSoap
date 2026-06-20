package com.dentalflow.pe.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dentalflow.pe.appointment.entity.Appointment;
import com.dentalflow.pe.treatment.entity.Treatment;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "tratamiento_id", nullable = true)
    private Treatment tratamiento;
    
    @OneToOne()
    @JoinColumn(name = "cita_id", nullable = true)
    private Appointment cita;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    public enum MetodoPago {
        EFECTIVO, TARJETA, TRANSFERENCIA, YAPE_PLIN
    }
}
