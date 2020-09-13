package com.example.demo.models.service.archivo;

import com.example.demo.models.entity.Archivo;
import com.example.demo.models.entity.Solicitud;

import java.util.List;

public interface SIArchivo {

    public Archivo findOne(Long id);

    public List<Archivo> findAll();

    public void save(Archivo archivo);

    public List<Archivo> findAllWithSolicitudNull();

    public List<Archivo> findAllBySolicitud(Solicitud solicitud);

    public void delete(Long id);
}
