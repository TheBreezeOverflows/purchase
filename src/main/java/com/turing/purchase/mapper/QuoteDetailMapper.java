package com.turing.purchase.mapper;

import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.QuoteDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuoteDetailMapper {
    int countByExample(QuoteDetailExample example);

    int deleteByExample(QuoteDetailExample example);

    int insert(QuoteDetail record);

    int insertSelective(QuoteDetail record);

    List<QuoteDetail> selectByExample(QuoteDetailExample example);

    int updateByExampleSelective(@Param("record") QuoteDetail record, @Param("example") QuoteDetailExample example);

    int updateByExample(@Param("record") QuoteDetail record, @Param("example") QuoteDetailExample example);
}