package com.example.demo.models.service.persona;

import com.example.demo.models.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SIPersona {
    public List<Persona> findAll();

    public void savePersona(Persona persona);

    public Persona findOne(Long id);

    public List<Persona> findByDniRuc(String term);

    public List<Persona> findByNombre(String term);
}
