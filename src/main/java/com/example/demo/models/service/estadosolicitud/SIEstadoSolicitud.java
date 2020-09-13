package com.example.demo.models.service.estadosolicitud;

import com.example.demo.models.entity.EstadoSolicitud;
import com.example.demo.models.entity.EstadoSolicitudPK;

import java.util.List;

public interface SIEstadoSolicitud {

    public void save(EstadoSolicitud estadoSolicitud);

    public List<EstadoSolicitud> findAll();

    public EstadoSolicitud findOne(EstadoSolicitudPK id);

    public List<EstadoSolicitud> findAllByIdEstadoSolicitudBySolicitud(Long id);


}
