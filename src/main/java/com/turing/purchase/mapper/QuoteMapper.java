package com.turing.purchase.mapper;

import com.turing.purchase.entity.Quote;
import com.turing.purchase.entity.QuoteExample;
import com.turing.purchase.entity.QuoteWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    //自定义方法
    List<Quote> selectQuoteAndStockSupplier(@Param("id") Integer supplierId,
                                            @Param("pageNum") Integer pageNum,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("sort")String sort,
                                            @Param("order")String order);

    @Select("select count(*) from quote where supplier_id = #{id}")
    int selectCount(@Param("id") Integer supplierId);

    int insertSelectiveReturnId(QuoteWithBLOBs record);
}