package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysUsers;
import com.turing.purchase.entity.SysUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface SysUsersMapper {
    int countByExample(SysUsersExample example);

    int deleteByExample(SysUsersExample example);

    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    List<SysUsers> selectByExample(SysUsersExample example);

    int updateByExampleSelective(@Param("record") SysUsers record, @Param("example") SysUsersExample example);

    int updateByExample(@Param("record") SysUsers record, @Param("example") SysUsersExample example);

    //自定义方法
    @Update("update sys_users set password = #{password} where login_id = #{loginId}")
    Integer updatePwdByLoginId(@Param("loginId") String loginId,@Param("password") String password);
}