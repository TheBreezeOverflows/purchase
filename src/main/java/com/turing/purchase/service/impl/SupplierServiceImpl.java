package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Supplier;
import com.turing.purchase.mapper.SupplierMapper;
import com.turing.purchase.service.SupplierService;
import com.turing.purchase.util.HandleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired(required = false)
    private SupplierMapper supplierMapper;

    @Override
    public Supplier getSupplierInfo(String userName) {
        Supplier supplier = supplierMapper.selectByUserName(userName);
        supplier.setStatus(HandleTool.getAllNoStr(supplier.getStatus()));
        supplier.setKind(HandleTool.getAllNoStr(supplier.getKind()));
        return supplier;
    }
}
