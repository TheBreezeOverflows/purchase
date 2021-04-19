package com.turing.purchase.service;

import com.turing.purchase.entity.Employee;

public interface EmployeeService {

    //获取用户信息
    Employee getEmpByUserName(String userName);

}
