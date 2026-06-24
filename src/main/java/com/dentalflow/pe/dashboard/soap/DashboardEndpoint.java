package com.dentalflow.pe.dashboard.soap;

import com.dentalflow.pe.dashboard.entity.Dashboard;
import com.dentalflow.pe.dashboard.serviceImpl.DashboardServiceImpl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@WebService(serviceName = "DashboardService")
@Component
@RequiredArgsConstructor
public class DashboardEndpoint {

    private final DashboardServiceImpl dashboardService;

    @WebMethod
    public Dashboard obtenerMetricas() {
        return dashboardService.obtenerMetricas();
    }
}