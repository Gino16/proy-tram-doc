package com.example.demo.models.dao;

import com.example.demo.models.entity.Voucher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DIVoucher extends CrudRepository<Voucher, Long> {

    @Query("select v from Voucher v where v.solicitud is null")
    public List<Voucher> findAllWithSolicitudNull();
}
