package com.dentalflow.pe;

import com.dentalflow.pe.appointment.serviceImpl.AppointmentServiceImpl;
import com.dentalflow.pe.appointment.serviceImpl.AppointmentTypeServiceImpl;
import com.dentalflow.pe.dentist.serviceImpl.DentistServiceImpl;
import com.dentalflow.pe.patient.serviceImpl.PatientServiceImpl;
import com.dentalflow.pe.specialty.serviceImpl.SpecialtyServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dentalflow.pe.dashboard.serviceImpl.DashboardServiceImpl;
import com.dentalflow.pe.material.serviceImpl.MaterialServiceImpl;

import jakarta.xml.ws.Endpoint;



@SpringBootApplication
public class ApiDentalFlowSoapApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =SpringApplication.run(ApiDentalFlowSoapApplication.class, args);
		
		MaterialServiceImpl service= context.getBean(MaterialServiceImpl.class);
		
		 DashboardServiceImpl dashboardService =context.getBean(DashboardServiceImpl.class);

		SpecialtyServiceImpl specialtyService = context.getBean(SpecialtyServiceImpl.class);

		DentistServiceImpl dentistService = context.getBean(DentistServiceImpl.class);

		AppointmentServiceImpl appointmentService =	context.getBean(AppointmentServiceImpl.class);

		AppointmentTypeServiceImpl appointmentTypeService = context.getBean(AppointmentTypeServiceImpl.class);

		PatientServiceImpl patientService =	context.getBean(PatientServiceImpl.class);

		Endpoint.publish("http://localhost:1520/ws/Appointment", appointmentService);

		Endpoint.publish("http://localhost:1520/ws/Dentist", dentistService);

		Endpoint.publish("http://localhost:1520/ws/Patient", patientService);

		Endpoint.publish("http://localhost:1520/ws/AppointmentType", appointmentTypeService);

		Endpoint.publish("http://localhost:1520/ws/Specialty", specialtyService);

		Endpoint.publish("http://localhost:1520/ws/Material", service);
		
		 Endpoint.publish("http://localhost:1520/ws/Dashboard",dashboardService);
	}

}
