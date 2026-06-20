package com.dentalflow.pe.treatment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dentalflow.pe.treatment.entity.TreatmentSession;

public interface ISesion_tratamientoRepository extends JpaRepository<TreatmentSession, Integer> {
	 int countByTratamiento_IdAndEstado(Integer idTratamiento, TreatmentSession.EstadoSesion estado);
	 
	 TreatmentSession findByTratamiento_IdAndEstado(Integer idTratamiento, TreatmentSession.EstadoSesion estado);
	 
	 List<TreatmentSession> findAllByTratamiento_Id(int id);
	 
}
