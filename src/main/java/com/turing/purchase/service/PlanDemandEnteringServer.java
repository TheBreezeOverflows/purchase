package com.turing.purchase.service;

import com.turing.purchase.entity.Material;

import java.util.List;

/**
 * 物资信息表的操作
 */
public interface PlanDemandEnteringServer {

    //查询计划录入可选择物资的所有
    public List<Material> FinAllMaterials();
    //根据id查询单个物资信息
    public Material FinSingleMaterials(long id);
    //根据物资编号查询物资信息
    public Material FinSingleMaterCode(String MateraCode);
    //根据物资姓名模糊查询获取所有与之有关的物资信息集合
    public List<Material> FinSingleMaterName(String MateraName);
}
