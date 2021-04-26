package com.turing.purchase.service;

import com.turing.purchase.entity.Orders;

import java.util.List;

public interface PlanOrdersService {
    //查询所有需求计划
    public List<Orders> FinAllOrder();
    //根据条件查询所有需求计划
    public List<Orders> FinAllOrderCondition(String matercode,String matername);
    //根据id查询单个需求计划
    public Orders FinbyOrder(long id);
    //插入单个需求计划
    public boolean insertOreder(Orders order);
    //根据id删除需求计划
    public boolean remaOrder(long id);
    //循环调用id删除方法
    public boolean CircularDeletion(String[] split);
    //修改指定需求计划
    public boolean UpdateOrder(Orders orders);
}
