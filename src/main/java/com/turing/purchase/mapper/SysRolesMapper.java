package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysRoles;
import com.turing.purchase.entity.SysRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolesMapper {
    int countByExample(SysRolesExample example);

    int deleteByExample(SysRolesExample example);

    int insert(SysRoles record);

    int insertSelective(SysRoles record);

    List<SysRoles> selectByExampleWithBLOBs(SysRolesExample example);

    List<SysRoles> selectByExample(SysRolesExample example);

    int updateByExampleSelective(@Param("record") SysRoles record, @Param("example") SysRolesExample example);

    int updateByExampleWithBLOBs(@Param("record") SysRoles record, @Param("example") SysRolesExample example);

    int updateByExample(@Param("record") SysRoles record, @Param("example") SysRolesExample example);
}