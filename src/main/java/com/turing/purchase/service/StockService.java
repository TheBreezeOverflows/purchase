package com.turing.purchase.service;

import com.turing.purchase.entity.Stock;
import com.turing.purchase.entity.StockWithBLOBs;
import sun.java2d.pipe.SolidTextRenderer;

import java.util.List;

public interface StockService {
    //查询所有采购计划
    public List<Stock> FinAllSotck();
    //根据id查询单个采购计划
    public Stock FinAllSotckById(long id);
    //根据条件查询采购计划指定采购类型的采购计划
    public List<Stock> FinAllSotckNameType(String StockName,String StockType);
    //添加采购计划
    public int insesrtStock(StockWithBLOBs stock);
    //修改采购计划
    public boolean updateStock(StockWithBLOBs stock);
    //根据id删除采购计划
    public  boolean deleteStock(long id);
    //添加定向求购对应的供应商
    public boolean StocksupplierById(long StockId,long suppliId);
    //根据采购id查询定向询价的供应商id集合
    public List<Long> FinAllStocksupplier(long stockid);
}
