package com.turing.purchase.service.impl;

import com.turing.purchase.entity.EasyUIDataGridJsonEntity;
import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.Supplier;
import com.turing.purchase.mapper.QuoteDetailMapper;
import com.turing.purchase.mapper.SupplierMapper;
import com.turing.purchase.service.SupplierService;
import com.turing.purchase.util.HandleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired(required = false)
    private SupplierMapper supplierMapper;
    @Autowired(required = false)
    private QuoteDetailMapper quoteDetailMapper;

    @Override
    public Supplier getSupplierInfo(String userName) {
        Supplier supplier = supplierMapper.selectByUserName(userName);
        supplier.setStatus(HandleTool.getAllNoStr(supplier.getStatus()));
        supplier.setKind(HandleTool.getAllNoStr(supplier.getKind()));
        return supplier;
    }

    @Override
    public List<QuoteDetail> getSupplierProducts(String userName,Integer pageNum,Integer pageSize,String sort,String order) {
        return quoteDetailMapper.selectByUserName(userName,pageNum,pageSize,sort,order);
    }

    @Override
    public EasyUIDataGridJsonEntity getSupplierProductsDaraGrid(String userName,Integer pageNum,Integer pageSize,String sort,String order) {
        List<QuoteDetail> list = quoteDetailMapper.selectByUserName(userName,(pageNum-1)*pageSize,pageSize,sort,order);
        //System.out.println(list.get(0).toString());
        EasyUIDataGridJsonEntity easyui = new EasyUIDataGridJsonEntity();
        easyui.setTotal(getTotalProducts(userName));
        easyui.setRows(list);
        return easyui;
    }

    @Override
    public int getTotalProducts(String userName) {
        return quoteDetailMapper.selectTotalCountByUserName(userName);
    }
}
