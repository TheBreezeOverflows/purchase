package com.turing.purchase.service;


import com.turing.purchase.entity.SysUsers;

public interface UserService {
    //注册
    void regist(SysUsers user);

    //查询
    SysUsers findByName(String name);
}
