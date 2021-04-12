package com.turing.purchase.mapper;

import com.turing.purchase.entity.EnquireDetail;
import com.turing.purchase.entity.EnquireDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnquireDetailMapper {
    int countByExample(EnquireDetailExample example);

    int deleteByExample(EnquireDetailExample example);

    int insert(EnquireDetail record);

    int insertSelective(EnquireDetail record);

    List<EnquireDetail> selectByExampleWithBLOBs(EnquireDetailExample example);

    List<EnquireDetail> selectByExample(EnquireDetailExample example);

    int updateByExampleSelective(@Param("record") EnquireDetail record, @Param("example") EnquireDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") EnquireDetail record, @Param("example") EnquireDetailExample example);

    int updateByExample(@Param("record") EnquireDetail record, @Param("example") EnquireDetailExample example);
}