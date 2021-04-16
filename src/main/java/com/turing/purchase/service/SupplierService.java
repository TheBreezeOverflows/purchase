package com.turing.purchase.service;

import com.turing.purchase.entity.Supplier;

public interface SupplierService {

    /**
     * 获取供应商信息
     *
     * @param userName 用户名
     * @return 供应商对象
     */
    Supplier getSupplierInfo(String userName);
}
