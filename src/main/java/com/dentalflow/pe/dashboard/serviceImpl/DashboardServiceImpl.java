package com.dentalflow.pe.dashboard.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.dentalflow.pe.appointment.repository.IAppointmentRepository;
import com.dentalflow.pe.dashboard.entity.Dashboard;
import com.dentalflow.pe.material.repository.IMaterialRepository;
import com.dentalflow.pe.payment.dto.PagoTotalMesDto;
import com.dentalflow.pe.payment.repository.IPagoRepository;
import com.dentalflow.pe.treatment.repository.ITratamientoRepository;
import com.dentalflow.pe.dashboard.service.DashboardService;

@Service
@PreAuthorize("hasRole('ADMIN') or hasRole('ODONTOLOGO')")
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
	@Override
	public List<PagoTotalMesDto> obtenerPagosTotalesUltimos5Meses() {
		System.out.println("ENTRASTE AL METODO DE OBTENER PAGOS 5 MESES");
		YearMonth fechaActual_AnioMes = YearMonth.now();
		
		YearMonth mesInicio = fechaActual_AnioMes.minusMonths(4);
		//primer dia a la primera hora posible
		LocalDateTime fechaInicio = mesInicio.atDay(1).atStartOfDay();
		//ultimo dia a la ultima hora posble
		LocalDateTime fechaFin = fechaActual_AnioMes.atEndOfMonth().atTime(23,59,59,999999999);
		
		List<PagoTotalMesDto> listaPagosMes = pagoRepository.sumMontoByMonthAndYearFecha(fechaInicio, fechaFin);
		
		for(PagoTotalMesDto fila : listaPagosMes) {
			System.out.println(fila.getAnio() + fila.getMes() +"|" + fila.getTotalPago()); 
		}
		
		if(listaPagosMes.size() ==0) {
			listaPagosMes.add(new PagoTotalMesDto(2026,01,BigDecimal.valueOf(2500)));
			listaPagosMes.add(new PagoTotalMesDto(2026,02,BigDecimal.valueOf(1500)));
			listaPagosMes.add(new PagoTotalMesDto(2026,03,BigDecimal.valueOf(1000)));
			listaPagosMes.add(new PagoTotalMesDto(2026,04,BigDecimal.valueOf(5000)));
			listaPagosMes.add(new PagoTotalMesDto(2026,05,BigDecimal.valueOf(3600)));
			return listaPagosMes;
			}
		return listaPagosMes;
	}

}
