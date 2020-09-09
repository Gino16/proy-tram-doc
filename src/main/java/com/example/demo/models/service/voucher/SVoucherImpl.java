package com.example.demo.models.service.voucher;

import com.example.demo.models.dao.DIVoucher;
import com.example.demo.models.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SVoucherImpl implements SIVoucher {

    @Autowired
    DIVoucher voucherDao;

    @Override
    public Voucher findOne(Long id) {
        return voucherDao.findById(id).orElse(null);
    }

    @Override
    public List<Voucher> findAll() {
        return (List<Voucher>) voucherDao.findAll();
    }

    @Override
    @Transactional
    public void save(Voucher voucher) {
        voucherDao.save(voucher);
    }

    @Override
    public List<Voucher> findAllWithSolicitudNull() {
        return voucherDao.findAllWithSolicitudNull();
    }

    @Override
    public void delete(Long id) {
        voucherDao.deleteById(id);
    }
}
