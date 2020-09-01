package com.example.demo.models.service.tiposolicitud;

import com.example.demo.models.entity.TipoSolicitud;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SITipoSolicitud {
    public List<TipoSolicitud> findAll();
}
