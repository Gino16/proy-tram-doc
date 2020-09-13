package com.example.demo.controller;

import com.example.demo.models.entity.*;
import com.example.demo.models.service.archivo.SIArchivo;
import com.example.demo.models.service.estado.SIEstado;
import com.example.demo.models.service.estadosolicitud.SIEstadoSolicitud;
import com.example.demo.models.service.persona.SIPersona;
import com.example.demo.models.service.solicitud.SISolicitud;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import com.example.demo.models.service.tiposolicitud.SITipoSolicitud;
import com.example.demo.models.service.voucher.SIVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/solicitud")
@SessionAttributes("solicitud")
public class CSolicitud {

	@Autowired
	SITipoSolicitud tipoSolicitudService;

	@Autowired
	SITipoArchivo tipoArchivoService;

	@Autowired
	SIArchivo archivoService;

	@Autowired
	SIVoucher voucherService;

	@Autowired
	SIPersona personaService;

	@Autowired
	SISolicitud solicitudService;

	@Autowired
	SIEstado estadoService;

	@Autowired
	SIEstadoSolicitud estadoSolicitudService;


	@GetMapping(value = "/registrar")
	public String vistaRegistrar(Model model){

		Solicitud solicitud = new Solicitud();
		model.addAttribute("titulo", "Formulario de Solicitud");
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("archivos", archivoService.findAllWithSolicitudNull());
		model.addAttribute("vouchers", voucherService.findAllWithSolicitudNull());
		model.addAttribute("tipoSolicitudes", tipoSolicitudService.findAll());

		return "solicitud/registrar";
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrar(@Valid Solicitud solicitud,
							Map<String, Object> model,
							@RequestParam("dniRucSolicitante") String dniRucSolicitante,
							@RequestParam("dniDestinatario") String dniDestinatario,
							SessionStatus status,
							RedirectAttributes flash){
		solicitud.setSolicitante(personaService.findOne(dniRucSolicitante));
		solicitud.setDestinatario(personaService.findOne(dniDestinatario));

		List<Voucher> misVouchers = voucherService.findAllWithSolicitudNull();
		List<Archivo> misArchivos = archivoService.findAllWithSolicitudNull();

		for(Voucher miVoucher: misVouchers){
			miVoucher.setSolicitud(solicitud);
			voucherService.save(miVoucher);
		}

		for (Archivo miArchivo : misArchivos){
			miArchivo.setSolicitud(solicitud);
			archivoService.save(miArchivo);
		}
		solicitudService.save(solicitud);

		EstadoSolicitudPK estadoSolicitudPK = new EstadoSolicitudPK();
		estadoSolicitudPK.setSolicitud(solicitud);
		//Siempre al crear tendra estado de registrado
		estadoSolicitudPK.setEstado(estadoService.finOne((long) 1));

		EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
		estadoSolicitud.setIdEstadoSolicitud(estadoSolicitudPK);

		estadoSolicitudService.save(estadoSolicitud);

		status.setComplete();
		flash.addFlashAttribute("success","Solicitud registrada con exito!");
		return "redirect:/solicitud/registrar";
	}

	@RequestMapping(value = "/listar")
	public String listar(Model model){
		List<Solicitud> solicitudes = solicitudService.findAll();
		model.addAttribute("ultimosEstados", getUltimoEstado(solicitudes));
		model.addAttribute("solicitudes", solicitudes);
		return "/solicitud/listar";
	}




	public List<Estado> getUltimoEstado(List<Solicitud> solicitudes){
		List<EstadoSolicitud> todosLosEstados = estadoSolicitudService.findAll();
		List<Estado> ultimoEstado = new ArrayList<>();
		Estado estado = null;
		for(Solicitud solicitud: solicitudes){
			for(EstadoSolicitud estadoSolicitud : todosLosEstados){
				if(solicitud.getIdSolicitud() == estadoSolicitud.getIdEstadoSolicitud().getSolicitud().getIdSolicitud()){
					estado = estadoSolicitud.getIdEstadoSolicitud().getEstado();
				}
			}
			ultimoEstado.add(estado);
		}
		return ultimoEstado;
	}
}
