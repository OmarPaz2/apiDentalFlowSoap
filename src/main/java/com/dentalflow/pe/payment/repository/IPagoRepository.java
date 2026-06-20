package com.dentalflow.pe.payment.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dentalflow.pe.payment.entity.Pago;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {
	
	@Query("SELECT COALESCE(SUM(p.monto), 0) FROM Pago p WHERE p.tratamiento.id = :idTratamiento")
    BigDecimal sumMontoByTratamiento_Id(Integer idTratamiento);
}
