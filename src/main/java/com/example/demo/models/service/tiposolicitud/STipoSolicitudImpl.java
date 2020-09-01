package com.example.demo.models.service.tiposolicitud;

import com.example.demo.models.dao.DITipoSolicitud;
import com.example.demo.models.entity.TipoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class STipoSolicitudImpl implements SITipoSolicitud {

    @Autowired
    DITipoSolicitud tipoSolicitudDao;

    @Override
    public List<TipoSolicitud> findAll() {
        return (List<TipoSolicitud>) tipoSolicitudDao.findAll();
    }
}
