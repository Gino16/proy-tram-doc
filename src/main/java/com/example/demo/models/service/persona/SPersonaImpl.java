package com.example.demo.models.service.persona;

import com.example.demo.models.dao.DIPersona;
import com.example.demo.models.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SPersonaImpl implements SIPersona {

    @Autowired
    private DIPersona personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void savePersona(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findOne(String id) {
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public List<Persona> findByDniRuc(String term) {
        return personaDao.findByDniRuc(term);
    }

    @Override
    public List<Persona> findByNombre(String term) {
        return personaDao.findByNombre(term);
    }
}
