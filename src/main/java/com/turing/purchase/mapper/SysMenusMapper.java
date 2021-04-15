package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysMenus;
import com.turing.purchase.entity.SysMenusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysMenusMapper {
    int countByExample(SysMenusExample example);

    int deleteByExample(SysMenusExample example);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    List<SysMenus> selectByExampleWithBLOBs(SysMenusExample example);

    List<SysMenus> selectByExample(SysMenusExample example);

    int updateByExampleSelective(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByExampleWithBLOBs(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByExample(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    //自定义sql
    @Select("select * from sys_menus where ID in ( select MENU_ID from sys_menu_role where ROLE_ID = ( select ROLE_ID from sys_user_role where USER_ID = ( select id from sys_users where LOGIN_ID = #{userName})))")
    List<SysMenus> getRoleMenus(@Param("userName")String userName);
}