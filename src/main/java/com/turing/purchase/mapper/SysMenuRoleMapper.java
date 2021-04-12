package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysMenuRole;
import com.turing.purchase.entity.SysMenuRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuRoleMapper {
    int countByExample(SysMenuRoleExample example);

    int deleteByExample(SysMenuRoleExample example);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    List<SysMenuRole> selectByExample(SysMenuRoleExample example);

    int updateByExampleSelective(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByExample(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);
}