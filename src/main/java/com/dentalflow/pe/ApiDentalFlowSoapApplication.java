package com.dentalflow.pe;

import com.dentalflow.pe.appointment.serviceImpl.AppointmentServiceImpl;
import com.dentalflow.pe.appointment.serviceImpl.AppointmentTypeServiceImpl;
import com.dentalflow.pe.auth.serviceImpl.AuthServiceImpl;
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
import org.springframework.aop.framework.AopProxyUtils;

@SpringBootApplication
public class ApiDentalFlowSoapApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =SpringApplication.run(ApiDentalFlowSoapApplication.class, args);
		
		MaterialServiceImpl service = context.getBean(MaterialServiceImpl.class);

		 DashboardServiceImpl dashboardService =context.getBean(DashboardServiceImpl.class);

		SpecialtyServiceImpl specialtyService = context.getBean(SpecialtyServiceImpl.class);

		ClinicalStaffServiceImpl dentistService = context.getBean(ClinicalStaffServiceImpl.class);

		AppointmentServiceImpl appointmentService =	context.getBean(AppointmentServiceImpl.class);

		AppointmentTypeServiceImpl appointmentTypeService = context.getBean(AppointmentTypeServiceImpl.class);

		PatientServiceImpl patientService =	context.getBean(PatientServiceImpl.class);

		AuthServiceImpl authService = context.getBean(AuthServiceImpl.class);

		PagoServiceImpl pagoService = context.getBean(PagoServiceImpl.class);

		SesionTratamientoServiceImpl sesionTratamientoService = context.getBean(SesionTratamientoServiceImpl.class);

		TratamientoServiceImpl tratamientoService = context.getBean(TratamientoServiceImpl.class);

		//

		Object realAppointmentService = AopProxyUtils.getSingletonTarget(appointmentService);
		Object realDentistService = AopProxyUtils.getSingletonTarget(dentistService);
		Object realPatientService = AopProxyUtils.getSingletonTarget(patientService);
		Object realAuthService = AopProxyUtils.getSingletonTarget(authService);
		Object realAppointmentTypeService = AopProxyUtils.getSingletonTarget(appointmentTypeService);
		Object realSpecialtyService = AopProxyUtils.getSingletonTarget(specialtyService);
		Object realDashboardService = AopProxyUtils.getSingletonTarget(dashboardService);
		Object realPagoService = AopProxyUtils.getSingletonTarget(pagoService);
		Object realSesionTratamientoService = AopProxyUtils.getSingletonTarget(sesionTratamientoService);
		Object realTratamientoService = AopProxyUtils.getSingletonTarget(tratamientoService);

		//

		Endpoint.publish("http://localhost:1520/ws/Appointment",
				realAppointmentService != null ? realAppointmentService : appointmentService);

		Endpoint.publish("http://localhost:1520/ws/Dentist",
				realDentistService != null ? realDentistService : dentistService); //falta

		Endpoint.publish("http://localhost:1520/ws/Patient",
				realPatientService != null ? realPatientService : patientService);

		Endpoint.publish("http://localhost:1520/ws/Auth",
				realAuthService != null ? realAuthService : authService);

		Endpoint.publish("http://localhost:1520/ws/AppointmentType",
				realAppointmentTypeService != null ? realAppointmentTypeService : appointmentTypeService); //falta

		Endpoint.publish("http://localhost:1520/ws/Specialty",
				realSpecialtyService != null ? realSpecialtyService : specialtyService); //falta

		Endpoint.publish("http://localhost:1520/ws/Material", service);

		 Endpoint.publish("http://localhost:1520/ws/Dashboard",
				 realDashboardService != null ? realDashboardService : dashboardService);

		 Endpoint.publish("http://localhost:1520/ws/Pago",
				 realPagoService != null ? realPagoService : pagoService);

		 Endpoint.publish("http://localhost:1520/ws/sesionTratamiento",
				 realSesionTratamientoService != null ? realSesionTratamientoService : sesionTratamientoService); //falta

		 Endpoint.publish("http://localhost:1520/ws/tratamiento",
				 realTratamientoService != null  ? realTratamientoService : tratamientoService);
	}

}
