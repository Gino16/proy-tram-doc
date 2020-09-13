package com.example.demo.models.service.archivo;

import com.example.demo.models.dao.DIArchivo;
import com.example.demo.models.entity.Archivo;
import com.example.demo.models.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SArchivoImpl implements SIArchivo {

    @Autowired
    DIArchivo archivoDao;

    @Override
    public Archivo findOne(Long id) {
        return archivoDao.findById(id).orElse(null);
    }

    @Override
    public List<Archivo> findAll() {
        return (List<Archivo>) archivoDao.findAll();
    }

    @Override
    @Transactional
    public void save(Archivo archivo) {
        archivoDao.save(archivo);
    }

    @Override
    public List<Archivo> findAllWithSolicitudNull() {
        return archivoDao.findAllWithSolicitudNull();
    }

    @Override
    public List<Archivo> findAllBySolicitud(Solicitud solicitud) {
        return archivoDao.findAllBySolicitud(solicitud);
    }

    @Override
    public void delete(Long id) {
        archivoDao.deleteById(id);
    }
}
