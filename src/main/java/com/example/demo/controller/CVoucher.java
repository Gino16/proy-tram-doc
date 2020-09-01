package com.example.demo.controller;

import com.example.demo.models.entity.Voucher;
import com.example.demo.models.service.SIUploadFile;
import com.example.demo.models.service.voucher.SIVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/voucher")
public class CVoucher {

    @Autowired
    SIUploadFile fileService;

    @Autowired
    SIVoucher voucherService;

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(@Valid Voucher voucher, Model model,
                            @RequestParam("file") MultipartFile fotoVoucher,
                            @RequestParam("fecha") String fecha){


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
        Date actualFecha = null;
        Date nuevaFecha = null;
        try {
            actualFecha = smpt1.parse(fecha);
            SimpleDateFormat smpt2 = new SimpleDateFormat("dd-MM-yyyy");
            nuevaFecha = new SimpleDateFormat("dd-MM-yyyy").parse(smpt2.format(actualFecha));
            voucher.setFechaPago(nuevaFecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        voucherService.save(voucher);

        return "redirect:/solicitud/registrar";
    }
}
