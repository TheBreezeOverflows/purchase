package com.turing.purchase.service.impl;

import com.turing.purchase.entity.IdMapping;
import com.turing.purchase.entity.IdMappingExample;
import com.turing.purchase.mapper.IdMappingMapper;
import com.turing.purchase.service.IdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdMappingImpl implements IdMappingService {

    //依赖mapper
    @Autowired(required = false)
    IdMappingMapper idMappingMapper;
    //编号对照表的初始添加
    @Override
    public boolean insertStockId(IdMapping idmap) {
        idMappingMapper.insertSelective(idmap);
        return false;
    }

    //根据采购id查询对应关联id编号
    @Override
    public IdMapping selectStockbyid(long id) {
        IdMappingExample example=new IdMappingExample();
        IdMappingExample.Criteria criteria = example.createCriteria();
        criteria.andStockIdEqualTo(id);
        List<IdMapping> idMappings = idMappingMapper.selectByExample(example);
        if (idMappings.size()>0){
           return idMappings.get(0);
        }
        return null;
    }

    //修改采购信息的对应状态信息
    @Override
    public boolean UpdateStockStatus(IdMapping idMapping) {
        IdMappingExample example =new IdMappingExample();
        IdMappingExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(idMapping.getId());
        int in = idMappingMapper.updateByExampleSelective(idMapping, example);
        if (in>0){
            return true;
        }
        return false;
    }
}
