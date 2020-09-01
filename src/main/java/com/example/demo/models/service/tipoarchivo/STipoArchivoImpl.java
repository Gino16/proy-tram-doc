package com.example.demo.models.service.tipoarchivo;

import com.example.demo.models.dao.DITipoArchivo;
import com.example.demo.models.entity.TipoArchivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class STipoArchivoImpl implements SITipoArchivo {

    @Autowired
    DITipoArchivo tipoArchivoDao;

    @Override
    public List<TipoArchivo> findAll() {
        return (List<TipoArchivo>) tipoArchivoDao.findAll();
    }

    @Override
    public TipoArchivo findById(Long id) {
        return tipoArchivoDao.findById(id).orElse(null);
    }


}
