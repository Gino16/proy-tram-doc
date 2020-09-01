package com.example.demo.models.dao;

import com.example.demo.models.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DIPersona extends CrudRepository<Persona, Long> {


    @Query("select p from Persona p join fetch p.puesto where p.dniRuc like %?1% ")
    public List<Persona> findByDniRuc(String term);

    @Query("select p from Persona p join fetch p.puesto where p.nombre like %?1% ")
    public List<Persona> findByNombre(String term);
}
