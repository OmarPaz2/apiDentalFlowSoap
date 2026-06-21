package com.dentalflow.pe.treatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dentalflow.pe.treatment.entity.Treatment;

public interface ITratamientoRepository extends JpaRepository<Treatment, Integer> {

	Treatment findByPaciente_Dni(String dni);
	
	 @Query("UPDATE Cita c SET c.pagado = :pagado WHERE c.id = :id")
	    int updateEstadoPago(Integer id,boolean pagado);
}
