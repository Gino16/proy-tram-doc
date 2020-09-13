package com.example.demo.models.service.voucher;

import com.example.demo.models.entity.Solicitud;
import com.example.demo.models.entity.Voucher;

import java.util.List;

public interface SIVoucher {

    public Voucher findOne(Long id);

    public List<Voucher> findAll();

    public void save(Voucher voucher);

    public List<Voucher> findAllWithSolicitudNull();

    public List<Voucher> findAllBySolicitud(Solicitud solicitud);

    public void delete(Long id);
}
