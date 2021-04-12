package com.turing.purchase.mapper;

import com.turing.purchase.entity.Quote;
import com.turing.purchase.entity.QuoteExample;
import com.turing.purchase.entity.QuoteWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuoteMapper {
    int countByExample(QuoteExample example);

    int deleteByExample(QuoteExample example);

    int insert(QuoteWithBLOBs record);

    int insertSelective(QuoteWithBLOBs record);

    List<QuoteWithBLOBs> selectByExampleWithBLOBs(QuoteExample example);

    List<Quote> selectByExample(QuoteExample example);

    int updateByExampleSelective(@Param("record") QuoteWithBLOBs record, @Param("example") QuoteExample example);

    int updateByExampleWithBLOBs(@Param("record") QuoteWithBLOBs record, @Param("example") QuoteExample example);

    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);
}