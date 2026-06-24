package com.dentalflow.pe.config;

import com.dentalflow.pe.auth.soap.AuthEndpoint;
import com.dentalflow.pe.patient.soap.PatientEndpoint;
import com.dentalflow.pe.appointment.soap.AppointmentEndpoint;
import com.dentalflow.pe.payment.soap.PagoEndpoint;
import com.dentalflow.pe.treatment.soap.TratamientoEndpoint;
import com.dentalflow.pe.treatment.soap.SesionTratamientoEndpoint;
import com.dentalflow.pe.specialty.soap.SpecialtyEndpoint;
import com.dentalflow.pe.material.soap.MaterialEndpoint;
import com.dentalflow.pe.dashboard.soap.DashboardEndpoint;

import com.dentalflow.pe.security.soap.SoapSecurityHelper;
import com.dentalflow.pe.security.soap.JwtSoapHandler;

import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.handler.Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SoapEndpointPublisherConfig {

    private static final String BASE_URL = "http://localhost:1520/ws";

    @Bean
    public Endpoint authSoapEndpoint(AuthEndpoint endpoint,
                                 SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Auth");
    }

    @Bean
    public Endpoint patientSoapEndpoint(PatientEndpoint endpoint,
                                    SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Patient");
    }

    @Bean
    public Endpoint appointmentSoapEndpoint(AppointmentEndpoint endpoint,
                                        SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Appointment");
    }

    @Bean
    public Endpoint paymentSoapEndpoint(PagoEndpoint endpoint,
                                    SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Payment");
    }

    @Bean
    public Endpoint treatmentSoapEndpoint(TratamientoEndpoint endpoint,
                                      SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Treatment");
    }

    @Bean
    public Endpoint sessionSoapEndpoint(SesionTratamientoEndpoint endpoint,
                                    SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Session");
    }

    @Bean
    public Endpoint specialtySoapEndpoint(SpecialtyEndpoint endpoint,
                                      SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Specialty");
    }

    @Bean
    public Endpoint materialSoapEndpoint(MaterialEndpoint endpoint,
                                     SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Material");
    }

    @Bean
    public Endpoint dashboardSoapEndpoint(DashboardEndpoint endpoint,
                                      SoapSecurityHelper helper) {
        return publish(endpoint, helper, "/Dashboard");
    }

    /**
     * MÉTODO CENTRALIZADO DE PUBLICACIÓN SOAP
     */
    private Endpoint publish(Object soapEndpoint,
                             SoapSecurityHelper helper,
                             String path) {

        Endpoint endpoint = Endpoint.create(soapEndpoint);

        List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();
        handlerChain.add(new JwtSoapHandler(helper));

        endpoint.getBinding().setHandlerChain(handlerChain);

        String fullUrl = BASE_URL + path;
        endpoint.publish(fullUrl);

        System.out.println("SOAP publicado en: " + fullUrl);

        return endpoint;
    }
}