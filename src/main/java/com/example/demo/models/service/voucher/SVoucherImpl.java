package com.example.demo.models.service.voucher;

import com.example.demo.models.dao.DIVoucher;
import com.example.demo.models.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SVoucherImpl implements SIVoucher {

    @Autowired
    DIVoucher voucherDao;

    @Override
    @Transactional
    public void save(Voucher voucher) {
        voucherDao.save(voucher);
    }
}
