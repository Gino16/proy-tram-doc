package com.example.demo.controller;

import com.example.demo.models.entity.Voucher;
import com.example.demo.models.service.SIUploadFile;
import com.example.demo.models.service.voucher.SIVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/voucher")
@SessionAttributes("voucher")
public class CVoucher {

    @Autowired
    SIUploadFile fileService;

    @Autowired
    SIVoucher voucherService;

    @GetMapping("/registrar")
    public String vistaRegistrar(Model model){
        Voucher voucher = new Voucher();
        model.addAttribute("voucher", voucher);
        return "voucher/registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@Valid Voucher voucher,
                            @RequestParam("file") MultipartFile fotoVoucher,
                            @RequestParam("fecha") String fecha,
                            RedirectAttributes flash,
                            SessionStatus status){


        if(!fotoVoucher.isEmpty()){
            if(voucher.getId() != null && voucher.getId() > 0 && voucher.getUrl() != null && voucher.getUrl().length() > 0){
                fileService.delete(voucher.getUrl());
            }
            String uniqueFilename = null;

            try{
                uniqueFilename = fileService.copy(fotoVoucher);
            } catch (IOException e) {
                e.printStackTrace();
            }
            voucher.setUrl(uniqueFilename);
        }

        SimpleDateFormat smpt1 = new SimpleDateFormat("yyyy-MM-dd");
        Date actualFecha;
        Date nuevaFecha;
        try {
            actualFecha = smpt1.parse(fecha);
            SimpleDateFormat smpt2 = new SimpleDateFormat("dd-MM-yyyy");
            nuevaFecha = new SimpleDateFormat("dd-MM-yyyy").parse(smpt2.format(actualFecha));
            voucher.setFechaPago(nuevaFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String mensaje = (voucher.getId() != null) ? "Voucher editado con exito!" : "Voucher registrado con exito!";
        voucherService.save(voucher);
        status.setComplete();
        flash.addFlashAttribute("success",mensaje);

        return "redirect:/solicitud/registrar";
    }

    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash){
        Voucher voucher = null;
        if (id > 0){
            voucher = voucherService.findOne(id);
            if(voucher == null){
                flash.addFlashAttribute("error", "El voucher no existe en la BD!");
                return "redirect:/solicitud/registrar";
            }
        } else {
            flash.addFlashAttribute("error","ID no valido!");
            return "redirect:/solicitud/registrar";
        }
        model.put("voucher", voucher);
        return "/voucher/registrar";
    }

    @GetMapping(value = "/ver/{id}", produces = "application/json")
    public @ResponseBody Voucher ver(@PathVariable Long id){
        return voucherService.findOne(id);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id > 0 ){
            Voucher voucher = voucherService.findOne(id);
            voucherService.delete(id);
            flash.addFlashAttribute("error", "Voucher eliminado con exito!");
            if(fileService.delete(voucher.getUrl())){
                flash.addFlashAttribute("info", "Se elimino el pdf adjunto al voucher");
            }

        }
        return "redirect:/solicitud/registrar";
    }
}
