package com.example.demo.models.service.solicitud;

import com.example.demo.models.entity.Solicitud;

import java.util.List;

public interface SISolicitud {
    public void save(Solicitud solicitud);

    public List<Solicitud> findAll();

    public Solicitud findOne(Long id);

    public List<Solicitud> findByPersonaDni(String dni);

    public List<Solicitud> findByPersonaNombre(String nombre);
}
