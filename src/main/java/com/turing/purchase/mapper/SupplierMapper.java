package com.turing.purchase.mapper;

import com.turing.purchase.entity.Supplier;
import com.turing.purchase.entity.SupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SupplierMapper {
    int countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    //自定义方法

    //查询供应商信息
    @Select("select * from supplier where user_id = (select id from sys_users where LOGIN_ID = #{userName})")
    Supplier selectByUserName(@Param("userName") String userName);

    @Select("select id from supplier where user_id = (select id from sys_users where LOGIN_ID = #{userName})")
    Integer selectIdByUserName(@Param("userName")String userName);
}