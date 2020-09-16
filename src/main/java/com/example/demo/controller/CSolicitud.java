package com.example.demo.controller;

import com.example.demo.models.entity.*;
import com.example.demo.models.service.archivo.SIArchivo;
import com.example.demo.models.service.estado.SIEstado;
import com.example.demo.models.service.estadosolicitud.SIEstadoSolicitud;
import com.example.demo.models.service.persona.SIPersona;
import com.example.demo.models.service.solicitud.SISolicitud;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import com.example.demo.models.service.tiposolicitud.SITipoSolicitud;
import com.example.demo.models.service.usuario.SUsuario;
import com.example.demo.models.service.voucher.SIVoucher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/solicitud")
@SessionAttributes("solicitud")
public class CSolicitud {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	SITipoSolicitud tipoSolicitudService;


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

	@Autowired
	SUsuario usuarioService;



	@GetMapping(value = "/registrar")
	public String vistaRegistrar(Model model, Authentication authentication){

		if (authentication != null){
			logger.info("Hola USER ".concat(authentication.getName()));
		}

		//Otra manera de obtener al usuario loggeado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth != null){
			logger.info("Hola USER OBTENIDO DE FORMA ESTATICA".concat(auth.getName()));
		}

		//Comprobar si tiene un rol
		if (hasRole("ROLE_USER")){
			logger.info("Hellow " + auth.getName() + " eres USER");
		}

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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioActual = usuarioService.findByUsername(auth.getName());
		List<Solicitud> solicitudes = solicitudService.findAll();
		model.addAttribute("ultimosEstados", getUltimoEstado(solicitudes));
		model.addAttribute("solicitudes", solicitudes);
		model.addAttribute("usuarioActual", usuarioActual);
		return "/solicitud/listar";
	}

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id,Model model, RedirectAttributes flash){
		Solicitud solicitud = null;
		if(id > 0){
			solicitud = solicitudService.findOne(id);
			if(solicitud == null){
				flash.addFlashAttribute("error", "Esta solicitud no se encuentra en la BD");
				return "redirect:/solicitud/listar";
			}
		} else {
			flash.addFlashAttribute("error", "La ID ingresada no es valida!");
			return "redirect:/solicitud/listar";
		}
		model.addAttribute("titulo", "Informacion de la Solicitud");
		model.addAttribute("estados", estadoSolicitudService.findAllByIdEstadoSolicitudBySolicitud(solicitud.getIdSolicitud()));
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("archivos", archivoService.findAllBySolicitud(solicitud));
		model.addAttribute("vouchers", voucherService.findAllBySolicitud(solicitud));
		model.addAttribute("aprobado", estadoService.finOne((long)3));
		model.addAttribute("desaprobado", estadoService.finOne((long)4));
		return "/solicitud/ver";
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

	private boolean hasRole(String role){
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null){
			return false;
		}

		Authentication authentication = context.getAuthentication();

		if(authentication == null){
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority authority: authorities){
			if(role.equals(authority.getAuthority())){
				return true;
			}
		}

		return false;

	}
}
