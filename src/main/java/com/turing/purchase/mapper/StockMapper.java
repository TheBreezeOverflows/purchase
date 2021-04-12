package com.turing.purchase.mapper;

import com.turing.purchase.entity.Stock;
import com.turing.purchase.entity.StockExample;
import com.turing.purchase.entity.StockWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockMapper {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int insert(StockWithBLOBs record);

    int insertSelective(StockWithBLOBs record);

    List<StockWithBLOBs> selectByExampleWithBLOBs(StockExample example);

    List<Stock> selectByExample(StockExample example);

    int updateByExampleSelective(@Param("record") StockWithBLOBs record, @Param("example") StockExample example);

    int updateByExampleWithBLOBs(@Param("record") StockWithBLOBs record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);
}