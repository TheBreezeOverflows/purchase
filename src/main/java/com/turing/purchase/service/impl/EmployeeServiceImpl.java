package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Employee;
import com.turing.purchase.mapper.EmployeeMapper;
import com.turing.purchase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired(required = false)
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmpByUserName(String userName) {
        return employeeMapper.selectByUserName(userName);
    }
}
