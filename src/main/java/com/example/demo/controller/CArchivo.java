package com.example.demo.controller;

import com.example.demo.models.entity.Archivo;
import com.example.demo.models.entity.Solicitud;
import com.example.demo.models.service.SIUploadFile;
import com.example.demo.models.service.archivo.SIArchivo;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/archivo")
@SessionAttributes("archivo")
public class CArchivo {

    @Autowired
    SIUploadFile uploadFileService;

    @Autowired
    SIArchivo archivoService;

    @Autowired
    SITipoArchivo tipoArchivoService;

    @GetMapping("/registrar")
    public String vistaRegistrar(Model model) {
        Archivo archivo = new Archivo();
        model.addAttribute("archivo", archivo);
        model.addAttribute("tipoArchivos", tipoArchivoService.findAll());
        return "archivo/registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid Archivo archivo,
                            @RequestParam("file") MultipartFile fotoArchivo,
                            @RequestParam("idTipoArchivo") Long idTipoArchivo,
                            RedirectAttributes flash,
                            SessionStatus status) {

        if (!fotoArchivo.isEmpty()) {
            if (archivo.getId() != null && archivo.getId() > 0 && archivo.getUrl() != null && archivo.getUrl().length() > 0) {
                uploadFileService.delete(archivo.getUrl());
            }
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(fotoArchivo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            archivo.setUrl(uniqueFilename);
        }

        String mensaje = (archivo.getId() != null) ? "Archivo editado con exito!" : "Archivo registrado con exito!";

        archivo.setTipoArchivo(tipoArchivoService.findById(idTipoArchivo));
        archivoService.save(archivo);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);

        return "redirect:/solicitud/registrar";
    }

    @RequestMapping(value = "/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Archivo archivo = null;
        if (id > 0) {
            archivo = archivoService.findOne(id);
            if (archivo == null) {
                flash.addFlashAttribute("error", "El archivo no existe en la BD!");
                return "redirect:/solicitud/registrar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no es valido!");
            return "redirect:/solicitud/registrar";
        }
        model.addAttribute("archivo", archivo);
        model.addAttribute("tipoArchivos", tipoArchivoService.findAll());
        return "/archivo/registrar";
    }

    @GetMapping(value = "/ver/{id}", produces = "application/json")
    public @ResponseBody
    Archivo ver(@PathVariable Long id) {
        return archivoService.findOne(id);
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            Archivo archivo = archivoService.findOne(id);
            archivoService.delete(id);
            flash.addFlashAttribute("error", "Archivo eliminado con exito!");
            if (uploadFileService.delete(archivo.getUrl())) {
                flash.addFlashAttribute("info", "El pdf adjunto al archivo fue eliminado");
            }
        } else {
            flash.addFlashAttribute("error", "La ID del archivo no es valido");
            return "redirect:/solicitud/registrar";
        }
        return "redirect:/solicitud/registrar";
    }

    @GetMapping("/verPdf/{id}")
    public @ResponseBody
    void generarPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
        Archivo archivo = archivoService.findOne(id);
        byte[] pdf = uploadFileService.getBytes(archivo.getUrl());
        streamPdf(response, pdf, archivo.getUrl().split("_")[1]);
    }

    private void streamPdf(HttpServletResponse response, byte[] pdf, String name) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=" + name);
        response.setContentLength(pdf.length);

        response.getOutputStream().write(pdf);
        response.getOutputStream().flush();
    }
}
