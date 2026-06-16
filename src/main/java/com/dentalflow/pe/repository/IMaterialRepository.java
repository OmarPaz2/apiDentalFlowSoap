package com.dentalflow.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dentalflow.pe.entity.Material;

public interface IMaterialRepository extends JpaRepository<Material, Integer> {

	List<Material> findByStockLessThanEqual(Integer stock);

}
