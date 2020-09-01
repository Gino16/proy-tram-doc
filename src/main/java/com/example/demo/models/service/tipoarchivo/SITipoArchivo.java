package com.example.demo.models.service.tipoarchivo;

import com.example.demo.models.entity.TipoArchivo;

import java.util.List;

public interface SITipoArchivo {
    public List<TipoArchivo> findAll();

    public TipoArchivo findById(Long id);
}
