package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysCodes;
import com.turing.purchase.entity.SysCodesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCodesMapper {
    int countByExample(SysCodesExample example);

    int deleteByExample(SysCodesExample example);

    int insert(SysCodes record);

    int insertSelective(SysCodes record);

    List<SysCodes> selectByExampleWithBLOBs(SysCodesExample example);

    List<SysCodes> selectByExample(SysCodesExample example);

    int updateByExampleSelective(@Param("record") SysCodes record, @Param("example") SysCodesExample example);

    int updateByExampleWithBLOBs(@Param("record") SysCodes record, @Param("example") SysCodesExample example);

    int updateByExample(@Param("record") SysCodes record, @Param("example") SysCodesExample example);
}