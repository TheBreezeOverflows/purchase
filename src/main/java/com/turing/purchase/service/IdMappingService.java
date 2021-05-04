package com.turing.purchase.service;

import com.turing.purchase.entity.IdMapping;

public interface IdMappingService {

    //插入编号对照表的采购编号和物资编号采购状态
    boolean insertStockId(IdMapping idmap);
    //根据采购计划查询编号对应信息
    IdMapping selectStockbyid(long id);
    //修改对应采购状态信息
    boolean UpdateStockStatus(IdMapping idMapping);
}
