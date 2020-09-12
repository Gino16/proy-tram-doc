package com.example.demo.models.service.solicitud;

import com.example.demo.models.dao.DISolicitud;
import com.example.demo.models.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SSolicitudImpl implements SISolicitud {

    @Autowired
    private DISolicitud solicitudDao;
    @Override
    public void save(Solicitud solicitud) {
        solicitudDao.save(solicitud);
    }

    @Override
    public List<Solicitud> findAll() {
        return (List<Solicitud>) solicitudDao.findAll();
    }

    @Override
    public Solicitud findOne(Long id) {
        return solicitudDao.findById(id).orElse(null);
    }

    @Override
    public List<Solicitud> findByPersonaDni(String dni) {
        return null;
    }

    @Override
    public List<Solicitud> findByPersonaNombre(String nombre) {
        return null;
    }
}
