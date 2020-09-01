package com.example.demo.models.service.archivo;

import com.example.demo.models.dao.DIArchivo;
import com.example.demo.models.entity.Archivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SArchivoImpl implements SIArchivo {

    @Autowired
    DIArchivo archivoDao;

    @Override
    @Transactional
    public void save(Archivo archivo) {
        archivoDao.save(archivo);
    }
}
