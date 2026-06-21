package com.dentalflow.pe.treatment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dentalflow.pe.treatment.entity.TreatmentSession;
import com.dentalflow.pe.treatment.entity.TreatmentSession.EstadoSesion;

public interface ISesion_tratamientoRepository extends JpaRepository<TreatmentSession, Integer> {
	 int countByTratamiento_IdAndEstado(Integer idTratamiento, TreatmentSession.EstadoSesion estado);
	 
	 TreatmentSession findByTratamiento_IdAndEstado(Integer idTratamiento, TreatmentSession.EstadoSesion estado);
	 
	 List<TreatmentSession> findAllByTratamiento_Id(int id);
	 
	   @Query("SELECT st FROM TreatmentSession st WHERE DATE(st.fechaProgramada) = :fecha, AND st.estado = :estado")
	 List<TreatmentSession> findAllByFechaProgramada(@Param("fecha") LocalDate fecha,EstadoSesion estado);
	 
}
