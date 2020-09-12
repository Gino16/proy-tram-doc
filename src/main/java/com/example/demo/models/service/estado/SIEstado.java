package com.example.demo.models.service.estado;

import com.example.demo.models.entity.Estado;

import java.util.List;

public interface SIEstado {
    public List<Estado> findAll();

    public Estado finOne(Long id);
}
