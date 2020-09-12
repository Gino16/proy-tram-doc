package com.example.demo.models.dao;

import com.example.demo.models.entity.EstadoSolicitud;
import com.example.demo.models.entity.EstadoSolicitudPK;
import org.springframework.data.repository.CrudRepository;

public interface DIEstadoSolicitud extends CrudRepository<EstadoSolicitud, EstadoSolicitudPK> {
}
