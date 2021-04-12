package com.turing.purchase.mapper;

import com.turing.purchase.entity.Enquire;
import com.turing.purchase.entity.EnquireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnquireMapper {
    int countByExample(EnquireExample example);

    int deleteByExample(EnquireExample example);

    int insert(Enquire record);

    int insertSelective(Enquire record);

    List<Enquire> selectByExampleWithBLOBs(EnquireExample example);

    List<Enquire> selectByExample(EnquireExample example);

    int updateByExampleSelective(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByExampleWithBLOBs(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByExample(@Param("record") Enquire record, @Param("example") EnquireExample example);
}