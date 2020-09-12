package com.example.demo.models.service.estado;

import com.example.demo.models.dao.DIEstado;
import com.example.demo.models.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SEstadoImpl implements SIEstado {

    @Autowired
    private DIEstado estadoDao;

    @Override
    public List<Estado> findAll() {
        return (List<Estado>) estadoDao.findAll();
    }

    @Override
    public Estado finOne(Long id) {
        return estadoDao.findById(id).orElse(null);
    }
}
