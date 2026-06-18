package com.dentalflow.pe.dashboard.service;

import com.dentalflow.pe.dashboard.entity.Dashboard;

import jakarta.jws.WebService;

@WebService
public interface DashboardService {
	
    Dashboard obtenerMetricas();
}
