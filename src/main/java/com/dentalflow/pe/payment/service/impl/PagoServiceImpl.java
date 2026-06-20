package com.dentalflow.pe.payment.service.impl;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dentalflow.pe.appointment.entity.Appointment;
import com.dentalflow.pe.appointment.repository.IAppointmentRepository;
import com.dentalflow.pe.payment.dto.PagoRequestDto;
import com.dentalflow.pe.payment.dto.PagoResponseDto;
import com.dentalflow.pe.payment.entity.Pago;
import com.dentalflow.pe.payment.mapper.PagoMapper;
import com.dentalflow.pe.payment.repository.IPagoRepository;
import com.dentalflow.pe.payment.service.PagoService;
import com.dentalflow.pe.treatment.entity.Treatment;
import com.dentalflow.pe.treatment.entity.TreatmentSession;
import com.dentalflow.pe.treatment.entity.TreatmentSession.EstadoSesion;
import com.dentalflow.pe.treatment.repository.ISesion_tratamientoRepository;
import com.dentalflow.pe.treatment.repository.ITratamientoRepository;

import jakarta.jws.WebService;

@WebService
@Component
public class PagoServiceImpl implements PagoService {

	private final IPagoRepository pagoRepository;
	private final ITratamientoRepository tratamientoRepository;
	private final ISesion_tratamientoRepository sesion_tratamientoRepository;
    private final PagoMapper pagoMapper;
    private final IAppointmentRepository citaRepository;
   

	public PagoServiceImpl(IPagoRepository pagoRepository, ITratamientoRepository tratamientoRepository,
			ISesion_tratamientoRepository sesion_tratamientoRepository, PagoMapper pagoMapper,
			IAppointmentRepository citaRepository) {
		super();
		this.pagoRepository = pagoRepository;
		this.tratamientoRepository = tratamientoRepository;
		this.sesion_tratamientoRepository = sesion_tratamientoRepository;
		this.pagoMapper = pagoMapper;
		this.citaRepository = citaRepository;
	}

	@Override
	public PagoResponseDto registerPagoTratamiento(PagoRequestDto pago,int idTratamiento) {
		Treatment tratamiento = tratamientoRepository.findById(idTratamiento).orElseThrow(()->new RuntimeException("tratamiento no encontrado"));
		
		if(tratamiento.getCostoEstimado().compareTo(pago.getMonto()) <0) {
			throw new RuntimeException("El monto a pagar no puede ser mayor que el costo del tratamiento");
		}
		
		TreatmentSession sesionProgramada = sesion_tratamientoRepository.findByTratamiento_IdAndEstado(idTratamiento, EstadoSesion.PROGRAMADA);
		 
		PagoResponseDto pagoRegisterEntity = new PagoResponseDto();
		if(sesionProgramada !=null) {
			
			BigDecimal montoRestante = tratamiento.getCostoEstimado().subtract(pagoRepository.sumMontoByTratamiento_Id(idTratamiento));
			if(pago.getMonto().compareTo(sesionProgramada.getCostoParcial()) ==0 || pago.getMonto().compareTo(montoRestante)<=0) {
				
				Pago pagoRegister = pagoMapper.toEntity(pago);
				pagoRegister.setTratamiento(tratamiento);
				
				 pagoRegisterEntity = pagoMapper.toResponsePagoTratamiento(pagoRepository.save(pagoRegister));
				
				pagoRegisterEntity.setRazon("TRATAMIENTO");
				
			}else {
				throw new RuntimeException("El monto a pagar no puede ser menor al monto de la sesion programda : "+sesionProgramada.getCostoParcial() );
						
			}
		}
		return pagoRegisterEntity;
	}

	@Override
	public PagoResponseDto registerPagoCita(PagoRequestDto pago,int idCita) {
		
		Appointment cita = citaRepository.findById(idCita).orElseThrow(()->new RuntimeException("cita no enocntrada"));
		if(cita.getAmount().compareTo(pago.getMonto())!=0) {
			throw new RuntimeException("El monto a pagar no puede ser diferente al precio de la cita: " + cita.getAmount());
		}
		
		Pago pagoEntity = pagoMapper.toEntity(pago);
		pagoEntity.setCita(cita);
		
		PagoResponseDto pagoRp = pagoMapper.toResponsePagoCita(pagoRepository.save(pagoEntity));
		pagoRp.setRazon("CITA");
		return pagoRp;
	}

	@Override
	public PagoResponseDto findPagoById(int idPago) {
		Pago pagoEntity = pagoRepository.findById(idPago).orElseThrow(()-> new RuntimeException("Registro de pago no encontrado"));
		
		if(pagoEntity.getCita() != null) {
			return pagoMapper.toResponsePagoCita(pagoEntity);
		}
		
		return pagoMapper.toResponsePagoTratamiento(pagoEntity);
		
	}

}
