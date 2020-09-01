package com.example.demo.controller;

import com.example.demo.models.entity.Archivo;
import com.example.demo.models.entity.Solicitud;
import com.example.demo.models.entity.Voucher;
import com.example.demo.models.service.persona.SIPersona;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import com.example.demo.models.service.tiposolicitud.SITipoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/solicitud")
@SessionAttributes("solicitud")
public class CSolicitud {

	@Autowired
	SITipoSolicitud tipoSolicitudService;

	@Autowired
	SITipoArchivo tipoArchivoService;


	@Autowired
	SIPersona personaService;


	@GetMapping(value = "/registrar")
	public String registrarSolicitud(Model model){

		Solicitud solicitud = new Solicitud();
		Archivo archivo = new Archivo();
		Voucher voucher = new Voucher();
		model.addAttribute("titulo", "Formulario de Solicitud");
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("archivo", archivo);
		model.addAttribute("voucher", voucher);
		model.addAttribute("tipoSolicitudes", tipoSolicitudService.findAll());
		model.addAttribute("tipoArchivos", tipoArchivoService.findAll());
		return "solicitud/registrar";
	}


}
