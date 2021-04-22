package com.turing.purchase.service;

import com.turing.purchase.entity.EasyUIDataGridJsonEntity;
import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.Supplier;

import java.util.List;

public interface SupplierService {

    /**
     * 获取供应商信息
     *
     * @param userName 用户名
     * @return 供应商对象
     */
    Supplier getSupplierInfo(String userName);

    //查询供应商产品（datagrid）
    EasyUIDataGridJsonEntity getSupplierProductsDataGrid(Integer pageNum,Integer pageSize,String sort,String order);

    //获取供应商产品总条数
    int getTotalProducts();

    //查询供应商产品类别（datagrid）
    EasyUIDataGridJsonEntity getSupplierProductsTypeDataGrid(Integer pageNum,Integer pageSize,String sort,String order);

    //获取供应商产品类别条数
    int getTotalProductsType();
}
