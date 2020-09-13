package com.example.demo.models.dao;

import com.example.demo.models.entity.EstadoSolicitud;
import com.example.demo.models.entity.EstadoSolicitudPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DIEstadoSolicitud extends CrudRepository<EstadoSolicitud, EstadoSolicitudPK> {

    @Query("select es from EstadoSolicitud es where es.idEstadoSolicitud.solicitud.idSolicitud = ?1")
    public List<EstadoSolicitud> findAllByIdEstadoSolicitudBySolicitud(Long id);
}
