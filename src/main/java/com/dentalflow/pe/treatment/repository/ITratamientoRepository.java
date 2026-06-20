package com.dentalflow.pe.treatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dentalflow.pe.treatment.entity.Treatment;

public interface ITratamientoRepository extends JpaRepository<Treatment, Integer> {

	Treatment findByPaciente_Dni(String dni);
}
