package com.turing.purchase.service.impl;

import com.turing.purchase.entity.SysUsers;
import com.turing.purchase.entity.SysUsersExample;
import com.turing.purchase.mapper.SysUsersMapper;
import com.turing.purchase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //@Resource
    @Autowired(required = false)
    private SysUsersMapper sysUsersMapper;
    //注册
    @Override
    public void regist(SysUsers use) {
        sysUsersMapper.insert(use);
    }

    //登录
    @Override
    public SysUsers findByName(String name) {
        System.out.println("用户："+name);
        SysUsersExample example =new SysUsersExample();
        SysUsersExample.Criteria create = example.createCriteria();
        create.andLoginIdEqualTo(name);
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(example);
        System.out.println("获取到的用户集合有:"+sysUsers.size());
        if (sysUsers.size()>0){
            System.out.println("是登录");
        return sysUsers.get(0);
        }
        return null;
    }
}
