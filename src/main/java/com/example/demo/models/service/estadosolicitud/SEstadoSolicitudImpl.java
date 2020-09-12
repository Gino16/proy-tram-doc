package com.example.demo.models.service.estadosolicitud;

import com.example.demo.models.dao.DIEstadoSolicitud;
import com.example.demo.models.entity.EstadoSolicitud;
import com.example.demo.models.entity.EstadoSolicitudPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SEstadoSolicitudImpl implements SIEstadoSolicitud {

    @Autowired
    private DIEstadoSolicitud estadoSolicitudDao;

    @Override
    public void save(EstadoSolicitud estadoSolicitud) {
        estadoSolicitudDao.save(estadoSolicitud);
    }

    @Override
    public List<EstadoSolicitud> findAll() {
        return (List<EstadoSolicitud>) estadoSolicitudDao.findAll();
    }

    @Override
    public EstadoSolicitud findOne(EstadoSolicitudPK id) {
        return estadoSolicitudDao.findById(id).orElse(null);
    }
}
