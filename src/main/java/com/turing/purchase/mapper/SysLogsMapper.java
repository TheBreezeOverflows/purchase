package com.turing.purchase.mapper;

import com.turing.purchase.entity.SysLogs;
import com.turing.purchase.entity.SysLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLogsMapper {
    int countByExample(SysLogsExample example);

    int deleteByExample(SysLogsExample example);

    int insert(SysLogs record);

    int insertSelective(SysLogs record);

    List<SysLogs> selectByExampleWithBLOBs(SysLogsExample example);

    List<SysLogs> selectByExample(SysLogsExample example);

    int updateByExampleSelective(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByExampleWithBLOBs(@Param("record") SysLogs record, @Param("example") SysLogsExample example);

    int updateByExample(@Param("record") SysLogs record, @Param("example") SysLogsExample example);
}