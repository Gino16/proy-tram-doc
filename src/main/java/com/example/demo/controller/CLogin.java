package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class CLogin {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout ,
                        Model model,
                        Principal principal,
                        RedirectAttributes flash) {
        if (principal != null) {
            flash.addFlashAttribute("info", "Ya ha iniciado sesion");
            return "redirect:/";
        }
        if(error != null){
            model.addAttribute("error", "Error en el login: Nombre Usuario o Contraseña Incorrecta, vuelva a intentarlo");
        }
        if (logout != null){
            model.addAttribute("success", "Ha cerrado sesion con exito!");
        }

        return "login";
    }
}
