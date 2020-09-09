package com.example.demo.controller;

import com.example.demo.models.entity.Archivo;
import com.example.demo.models.entity.Solicitud;
import com.example.demo.models.entity.Voucher;
import com.example.demo.models.service.archivo.SIArchivo;
import com.example.demo.models.service.persona.SIPersona;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import com.example.demo.models.service.tiposolicitud.SITipoSolicitud;
import com.example.demo.models.service.voucher.SIVoucher;
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
	SIArchivo archivoService;

	@Autowired
	SIVoucher voucherService;

	@Autowired
	SIPersona personaService;


	@GetMapping(value = "/registrar")
	public String registrar(Model model){

		Solicitud solicitud = new Solicitud();
		model.addAttribute("titulo", "Formulario de Solicitud");
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("archivos", archivoService.findAllWithSolicitudNull());
		model.addAttribute("vouchers", voucherService.findAllWithSolicitudNull());
		model.addAttribute("tipoSolicitudes", tipoSolicitudService.findAll());

		return "solicitud/registrar";
	}


}
