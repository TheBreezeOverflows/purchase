package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Orders;
import com.turing.purchase.entity.OrdersExample;
import com.turing.purchase.mapper.OrdersMapper;
import com.turing.purchase.service.PlanOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlanOrdersServiceImpl implements PlanOrdersService {

    @Autowired(required = false)
    private OrdersMapper ordersMapper;

    //查询所有需求计划
    @Override
    public List<Orders> FinAllOrder() {

        return ordersMapper.selectByExample(null);
    }


    //根据id查询单个需求计划
    @Override
    public Orders FinbyOrder(long id) {
        OrdersExample example =new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Orders> orders = ordersMapper.selectByExample(example);
        if (orders.size()>0) {
            return orders.get(0);
        }
        return null;
    }


    //插入一条需求计划
    @Override
    public boolean insertOreder(Orders order) {
        boolean fla=false;
        int in = ordersMapper.insert(order);
        if (in>0){
            fla=true;
        }
        return fla;
    }
}
