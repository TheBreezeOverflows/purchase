package com.turing.purchase.service.impl;

import com.turing.purchase.entity.*;
import com.turing.purchase.mapper.StockMapper;
import com.turing.purchase.mapper.StockSupplierMapper;
import com.turing.purchase.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StockServiceImpl implements StockService {

    //依赖mapper
    @Autowired(required = false)
    private StockMapper stockMapper;
    //定向求购对应供应商表
    @Autowired(required = false)
    private StockSupplierMapper stocksupmapper;
    //查询所有采购计划
    @Override
    public List<Stock> FinAllSotck() {
        return stockMapper.selectByExample(null);
    }

    //根据id查询单个采购计划
    @Override
    public Stock FinAllSotckById(long id) {
        StockExample example =new StockExample();
        StockExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Stock> stk = stockMapper.selectByExample(example);
        if (stk.size()>0){
            return stk.get(0);
        }
        return null;
    }

    //按采购姓名和类型模糊查询采购计划
    @Override
    public List<Stock> FinAllSotckNameType(String StockName, String StockType) {
        StockExample example =new StockExample();
        StockExample.Criteria criteria = example.createCriteria();
        //判断查询的采购姓名是否为为全部
        if (StockName!=""){
            criteria.andStockNameLike("%"+StockName+"%");
        }
        //判断查询的采购类型是否为全部
        if (StockType!=""){
            criteria.andStockTypeEqualTo(StockType);
        }

        return  stockMapper.selectByExample(example);
    }

    //添加采购计划
    @Override
    public int insesrtStock(StockWithBLOBs stock) {
        stockMapper.insertSelective(stock);

        return stock.getId().intValue();
    }

    //修改采购计划
    @Override
    public boolean updateStock(StockWithBLOBs stock) {
        StockExample example =new StockExample();
        StockExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(stock.getId());
        int in = stockMapper.updateByExampleSelective(stock, example);
        if (in>0){
            return true;
        }
        return false;
    }

    //根据id删除采购计划
    @Override
    public boolean deleteStock(long id) {
        StockExample example =new StockExample();
        StockExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int in = stockMapper.deleteByExample(example);
        if (in>0){
            return true;
        }
        return false;
    }

    //添加定向求购的对应供应商
    @Override
    public boolean StocksupplierById(long StockId, long suppliId) {
        StockSupplier stocksupplier =new StockSupplier();
        stocksupplier.setStockId(StockId);//添加采购id
        stocksupplier.setSupplierId(suppliId);//添加供应商id
        int in = stocksupmapper.insertSelective(stocksupplier);
        if (in>0){
            return true;
        }
        return false;
    }

    //根据采购id查询定向询价的供应商id集合
    @Override
    public List<Long> FinAllStocksupplier(long stockid) {
        StockSupplierExample example =new StockSupplierExample();
        StockSupplierExample.Criteria criteria = example.createCriteria();
        criteria.andStockIdEqualTo(stockid);
        List<StockSupplier> stockSuppliers = stocksupmapper.selectByExample(example);
        if (stockSuppliers.size()>0){
            List<Long> Stocksuperyid= new ArrayList<>();
            //遍历获取到的供应商集合
            for (StockSupplier stocksupter:stockSuppliers) {
                Stocksuperyid.add(stocksupter.getSupplierId());
            }
            return Stocksuperyid;
        }
        return null;
    }
}
