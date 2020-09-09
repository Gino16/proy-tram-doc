package com.example.demo.controller;

import com.example.demo.models.entity.Persona;
import com.example.demo.models.service.persona.SIPersona;
import com.example.demo.models.service.puesto.SIPuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/persona")
@SessionAttributes("persona")
public class CPersona {

    @Autowired
    SIPersona personaService;

    @Autowired
    SIPuesto puestoService;

    @GetMapping(value = "/registrar")
    public String vistaRegistrar(Model model){
        Persona persona = new Persona();

        model.addAttribute("persona", persona);
        model.addAttribute("titulo", "Registrar Solicitante");
        model.addAttribute("puestos", puestoService.findAll());
        return "persona/registrar";
    }

    @PostMapping(value = "/registrar")
    public String registrar(@Valid Persona persona, Model model, RedirectAttributes flash, SessionStatus status){
        personaService.savePersona(persona);
        status.setComplete();
        flash.addFlashAttribute("success", "Solicitante registrado con exito!");
        return "redirect:/solicitud/registrar";
    }

    @GetMapping(value = "/buscar-solicitante/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Persona> buscarSolicitante(@PathVariable String term){
        return personaService.findByDniRuc(term);
    }


    @GetMapping(value = "/buscar-destinatario/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Persona> buscarDestinatario(@PathVariable String term){
        return personaService.findByNombre(term);
    }
}
