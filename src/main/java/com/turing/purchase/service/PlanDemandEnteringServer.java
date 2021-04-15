package com.turing.purchase.service;

import com.turing.purchase.entity.Material;

import java.util.List;

public interface PlanDemandEnteringServer {

    //查询计划录入可选择物资的所有
    public List<Material> FinAllMaterials();
    //根据id查询单个物资信息
    public Material FinSingleMaterials(long id);

}
