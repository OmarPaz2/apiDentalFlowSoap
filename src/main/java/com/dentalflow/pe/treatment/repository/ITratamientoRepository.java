package com.dentalflow.pe.treatment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dentalflow.pe.treatment.entity.Treatment;
import com.dentalflow.pe.treatment.entity.Treatment.EstadoTratamiento;

public interface ITratamientoRepository extends JpaRepository<Treatment, Integer> {

	Treatment findByPaciente_Dni(String dni);
	
	 @Query("UPDATE Treatment c SET c.pagado = :pagado WHERE c.id = :id")
	    void updateEstadoPago(Integer id,boolean pagado);
	 
	 Treatment findByPaciente_DniAndEstadoIn(String dni, List<EstadoTratamiento> estados);
}
