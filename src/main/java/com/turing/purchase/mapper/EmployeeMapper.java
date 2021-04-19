package com.turing.purchase.mapper;

import com.turing.purchase.entity.Employee;
import com.turing.purchase.entity.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    int countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    //自定义方法

    //根据用户名查询员工信息（登录用户的信息）
    @Select("select * from employee where user_id = (select id from sys_users where LOGIN_ID = #{userName})")
    Employee selectByUserName(@Param("userName") String userName);
}