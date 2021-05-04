package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Orders;
import com.turing.purchase.entity.OrdersExample;
import com.turing.purchase.mapper.OrdersMapper;
import com.turing.purchase.service.PlanOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //根据条件查询
    @Override
    public List<Orders> FinAllOrderCondition(String matercode, String matername) {
        OrdersExample example =new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andMaterialCodeLike("%"+matercode+"%")
                .andMaterialNameLike("%"+matername+"%");
        return ordersMapper.selectByExample(example);
    }


    //根据条件加排序方式查询所有需求计划
    @Override
    public List<Orders> FinAllOrdercodenmaeorby(String matercode, String matername, String orby) {
        return ordersMapper.selectByExampleOrderby("%"+matercode+"%", "%"+matername+"%", orby);

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

    //根据id删除指定信息
    @Override
    public boolean remaOrder(long id) {
        OrdersExample example =new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        int in = ordersMapper.deleteByExample(example);
        if (in>0){
            return true;
        }
        return false;
    }
    //循环调用根据id删除方法
    @Override
    @Transactional
    public boolean CircularDeletion(String[] split){
        if (split.length>0) {
            for (String s : split) {
                remaOrder(Integer.parseInt(s));
            }
           return true;
        }
        return false;
    }

    //修改
    @Override
    public boolean UpdateOrder(Orders orders) {
        //获取当前修改的原数据
        OrdersExample example =new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(orders.getId());//id
        int in = ordersMapper.updateByExampleSelective(orders,example);
        if (in>0){
            return true;
        }
        return false;
    }
}
