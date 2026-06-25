package com.dentalflow.pe.dashboard.service;

import java.util.List;

import com.dentalflow.pe.dashboard.entity.Dashboard;
import com.dentalflow.pe.payment.dto.PagoTotalMesDto;

import jakarta.jws.WebService;

@WebService
public interface DashboardService {
	
    Dashboard obtenerMetricas();
    
    List<PagoTotalMesDto> obtenerPagosTotalesUltimos5Meses(); 
}
