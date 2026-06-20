package com.dentalflow.pe;

import com.dentalflow.pe.appointment.serviceImpl.AppointmentServiceImpl;
import com.dentalflow.pe.appointment.serviceImpl.AppointmentTypeServiceImpl;
import com.dentalflow.pe.clinicalStaff.serviceImpl.ClinicalStaffServiceImpl;
import com.dentalflow.pe.patient.serviceImpl.PatientServiceImpl;
import com.dentalflow.pe.payment.service.impl.PagoServiceImpl;
import com.dentalflow.pe.specialty.serviceImpl.SpecialtyServiceImpl;
import com.dentalflow.pe.treatment.service.impl.SesionTratamientoServiceImpl;
import com.dentalflow.pe.treatment.service.impl.TratamientoServiceImpl;

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

		ClinicalStaffServiceImpl dentistService = context.getBean(ClinicalStaffServiceImpl.class);

		AppointmentServiceImpl appointmentService =	context.getBean(AppointmentServiceImpl.class);

		AppointmentTypeServiceImpl appointmentTypeService = context.getBean(AppointmentTypeServiceImpl.class);

		PatientServiceImpl patientService =	context.getBean(PatientServiceImpl.class);
		
		PagoServiceImpl pagoService = context.getBean(PagoServiceImpl.class);
		SesionTratamientoServiceImpl sesionTratamientoService = context.getBean(SesionTratamientoServiceImpl.class);
		TratamientoServiceImpl tratamientoService = context.getBean(TratamientoServiceImpl.class);

		Endpoint.publish("http://localhost:1520/ws/Appointment", appointmentService);

		Endpoint.publish("http://localhost:1520/ws/Dentist", dentistService);

		Endpoint.publish("http://localhost:1520/ws/Patient", patientService);

		Endpoint.publish("http://localhost:1520/ws/AppointmentType", appointmentTypeService);

		Endpoint.publish("http://localhost:1520/ws/Specialty", specialtyService);

		Endpoint.publish("http://localhost:1520/ws/Material", service);
		
		 Endpoint.publish("http://localhost:1520/ws/Dashboard",dashboardService);
		 Endpoint.publish("http://localhost:1520/ws/Pago",pagoService);
		 Endpoint.publish("http://localhost:1520/ws/sesionTratamiento",sesionTratamientoService);
		 Endpoint.publish("http://localhost:1520/ws/tratamiento",tratamientoService);
	}

}
