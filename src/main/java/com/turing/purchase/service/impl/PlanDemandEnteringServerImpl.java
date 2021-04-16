package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.MaterialExample;
import com.turing.purchase.mapper.MaterialMapper;
import com.turing.purchase.service.PlanDemandEnteringServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanDemandEnteringServerImpl implements PlanDemandEnteringServer {

    @Autowired(required = false)
    private MaterialMapper materMapper;

    //查询计划录入可选择物资的所有
    @Override
    public List<Material> FinAllMaterials() {

        return materMapper.selectByExample(null);
    }

    //根据查询单个物资信息
    @Override
    public Material FinSingleMaterials(long id) {
        MaterialExample example =new MaterialExample();
        MaterialExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Material> materials = materMapper.selectByExample(example);
        System.out.println("获取到的物资有:"+materials.size());
        if (materials.size()>0){
            return materials.get(0);
        }
        return null;
    }
}
