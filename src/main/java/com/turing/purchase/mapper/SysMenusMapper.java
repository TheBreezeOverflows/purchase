package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysMenus;
import com.turing.purchase.entity.SysMenusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}