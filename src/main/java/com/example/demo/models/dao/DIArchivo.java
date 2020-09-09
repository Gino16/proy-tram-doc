package com.example.demo.models.dao;

import com.example.demo.models.entity.Archivo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DIArchivo extends CrudRepository<Archivo, Long> {

    @Query("select a from Archivo a join fetch a.tipoArchivo where a.solicitud is null")
    public List<Archivo> findAllWithSolicitudNull();
}
