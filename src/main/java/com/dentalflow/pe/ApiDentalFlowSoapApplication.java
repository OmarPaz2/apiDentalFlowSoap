package com.dentalflow.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dentalflow.pe.serviceImpl.DashboardServiceImpl;
import com.dentalflow.pe.serviceImpl.MaterialServiceImpl;

import jakarta.xml.ws.Endpoint;



@SpringBootApplication
public class ApiDentalFlowSoapApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =SpringApplication.run(ApiDentalFlowSoapApplication.class, args);
		
		MaterialServiceImpl service= context.getBean(MaterialServiceImpl.class);
		
		 DashboardServiceImpl dashboardService =context.getBean(DashboardServiceImpl.class);
		
		
		Endpoint.publish("http://localhost:1520/ws/Material", service);
		
		 Endpoint.publish("http://localhost:1520/ws/Dashboard",dashboardService);
	}

}
