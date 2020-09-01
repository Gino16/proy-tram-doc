package com.example.demo.models.service.puesto;

import com.example.demo.models.dao.DIPuesto;
import com.example.demo.models.entity.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SPuestoImpl implements SIPuesto {

    @Autowired
    DIPuesto puestoDao;

    @Override
    public List<Puesto> findAll() {
        return (List<Puesto>) puestoDao.findAll();
    }
}
