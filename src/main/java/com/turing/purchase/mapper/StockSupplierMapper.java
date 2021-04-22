package com.turing.purchase.mapper;

import com.turing.purchase.entity.StockSupplier;
import com.turing.purchase.entity.StockSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockSupplierMapper {
    int countByExample(StockSupplierExample example);

    int deleteByExample(StockSupplierExample example);

    int insert(StockSupplier record);

    int insertSelective(StockSupplier record);

    List<StockSupplier> selectByExample(StockSupplierExample example);

    int updateByExampleSelective(@Param("record") StockSupplier record, @Param("example") StockSupplierExample example);

    int updateByExample(@Param("record") StockSupplier record, @Param("example") StockSupplierExample example);

    //自定义方法
    List<StockSupplier> selectStockSupplier();
}