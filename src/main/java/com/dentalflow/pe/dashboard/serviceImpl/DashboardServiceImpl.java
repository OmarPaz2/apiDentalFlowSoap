package com.dentalflow.pe.dashboard.serviceImpl;

import org.springframework.stereotype.Service;

import com.dentalflow.pe.appointment.repository.IAppointmentRepository;
import com.dentalflow.pe.dashboard.entity.Dashboard;
import com.dentalflow.pe.material.repository.IMaterialRepository;
import com.dentalflow.pe.payment.repository.IPagoRepository;
import com.dentalflow.pe.treatment.repository.ITratamientoRepository;
import com.dentalflow.pe.dashboard.service.DashboardService;

import jakarta.jws.WebService;

@Service
@WebService
public class DashboardServiceImpl implements DashboardService {

	
	private final IMaterialRepository materialRepository;
	private final IPagoRepository pagoRepository;
	private final IAppointmentRepository appointmentRepository;
	private final ITratamientoRepository treatmentRepository;

	public DashboardServiceImpl(
            IMaterialRepository materialRepository,
            IPagoRepository pagoRepository,
            IAppointmentRepository appointmentRepository,
            ITratamientoRepository treatmentRepository) {

        this.materialRepository = materialRepository;
        this.pagoRepository = pagoRepository;
        this.appointmentRepository = appointmentRepository;
        this.treatmentRepository = treatmentRepository;
    }
	@Override
	public Dashboard obtenerMetricas() {
		 Dashboard dashboard = new Dashboard();

	        // Total de citas
	        dashboard.setCitasDelDia(appointmentRepository.count());

	        // Total de tratamientos
	        dashboard.setTratamientosActivos(treatmentRepository.count());

	        // Total de pagos
	        dashboard.setPagosRealizados(pagoRepository.count());

	        // Materiales con stock crítico
	        long stockCritico = materialRepository.findAll()
	                .stream()
	                .filter(m -> m.getStock() <= m.getStockMinimo())
	                .count();

	        dashboard.setStockCritico(stockCritico);

	        return dashboard;
	}

}
