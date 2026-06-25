package com.dentalflow.pe.payment.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalflow.pe.payment.dto.PagoTotalMesDto;
import com.dentalflow.pe.payment.entity.Pago;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {
	
	@Query("SELECT COALESCE(SUM(p.monto), 0) FROM Pago p WHERE p.tratamiento.id = :idTratamiento")
    BigDecimal sumMontoByTratamiento_Id(Integer idTratamiento);
	
	@Query("SELECT FUNCTION('YEAR', p.fecha), FUNCTION('MONTH', p.fecha), COALESCE(SUM(p.monto), 0) " +
		       "FROM Pago p " +
		       "WHERE p.fecha BETWEEN :desde AND :hasta " +
		       "GROUP BY FUNCTION('YEAR', p.fecha), FUNCTION('MONTH', p.fecha)")
	
	List<PagoTotalMesDto> sumMontoByMonthAndYearFecha(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);
	
}
