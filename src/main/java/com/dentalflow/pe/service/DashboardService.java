package com.dentalflow.pe.service;

import com.dentalflow.pe.entity.Dashboard;

import jakarta.jws.WebService;

@WebService
public interface DashboardService {
	
    Dashboard obtenerMetricas();
}
