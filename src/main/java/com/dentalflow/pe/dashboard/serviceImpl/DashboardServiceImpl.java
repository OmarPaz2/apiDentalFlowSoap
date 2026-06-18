package com.dentalflow.pe.dashboard.serviceImpl;

import org.springframework.stereotype.Service;

import com.dentalflow.pe.dashboard.entity.Dashboard;
import com.dentalflow.pe.material.repository.IMaterialRepository;
import com.dentalflow.pe.dashboard.service.DashboardService;

import jakarta.jws.WebService;

@Service
@WebService
public class DashboardServiceImpl implements DashboardService {

	
	private final IMaterialRepository materialRepository;

    public DashboardServiceImpl(IMaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
    
	@Override
	public Dashboard obtenerMetricas() {
		Dashboard dashboard = new Dashboard();

        dashboard.setCitasDelDia(0L);

        dashboard.setTratamientosActivos(0L);

        dashboard.setPagosRealizados(0L);

        long stockCritico =
                materialRepository.findAll()
                        .stream()
                        .filter(m -> m.getStock() <= m.getStockMinimo())
                        .count();

        dashboard.setStockCritico(stockCritico);

        return dashboard;
	}

}
