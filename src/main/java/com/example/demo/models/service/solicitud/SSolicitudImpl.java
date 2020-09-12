package com.example.demo.models.service.solicitud;

import com.example.demo.models.dao.DISolicitud;
import com.example.demo.models.entity.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSolicitudImpl implements SISolicitud {

    @Autowired
    private DISolicitud solicitudDao;
    @Override
    public void save(Solicitud solicitud) {
        solicitudDao.save(solicitud);
    }
}
