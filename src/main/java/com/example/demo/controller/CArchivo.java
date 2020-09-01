package com.example.demo.controller;

import com.example.demo.models.entity.Archivo;
import com.example.demo.models.service.SIUploadFile;
import com.example.demo.models.service.archivo.SIArchivo;
import com.example.demo.models.service.tipoarchivo.SITipoArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/archivo")
public class CArchivo {

    @Autowired
    SIUploadFile uploadFileService;

    @Autowired
    SIArchivo archivoService;

    @Autowired
    SITipoArchivo tipoArchivoService;

    @PostMapping("/registrar")
    public String guardarArchivo(@Valid Archivo archivo, Model model,
                                 @RequestParam("file") MultipartFile fotoArchivo,
                                 @RequestParam("idTipoArchivo") Long idTipoArchivo) {

        if (!fotoArchivo.isEmpty()) {
            if (archivo.getId() != null && archivo.getId() > 0 && archivo.getUrl() != null && archivo.getUrl().length() > 0) {
                uploadFileService.delete(archivo.getUrl());
            }
            String uniqueFilename = null;
            try{
                uniqueFilename = uploadFileService.copy(fotoArchivo);
            } catch (Exception e){
                e.printStackTrace();
            }

            archivo.setUrl(uniqueFilename);
        }

        archivo.setTipoArchivo(tipoArchivoService.findById(idTipoArchivo));
        archivoService.save(archivo);

        return "redirect:/solicitud/registrar";
    }
}
