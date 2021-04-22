package com.turing.purchase.mapper;

import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.QuoteDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuoteDetailMapper {
    int countByExample(QuoteDetailExample example);

    int deleteByExample(QuoteDetailExample example);

    int insert(QuoteDetail record);

    int insertSelective(QuoteDetail record);

    List<QuoteDetail> selectByExample(QuoteDetailExample example);

    int updateByExampleSelective(@Param("record") QuoteDetail record, @Param("example") QuoteDetailExample example);

    int updateByExample(@Param("record") QuoteDetail record, @Param("example") QuoteDetailExample example);

    //自定义方法

    /**
     * 查询 供应商产品信息 分页、排序
     * @param supplierId 供应商id
     * @param pageNum 开始行的下标
     * @param pageSize 查询多少行（查询行数）
     * @param sort 排序列名
     * @param order 排序方式
     * @return
     */
    List<QuoteDetail> selectBySupplierId(@Param("id") Integer supplierId,
                                       @Param("pageNum") Integer pageNum,
                                       @Param("pageSize") Integer pageSize,
                                       @Param("sort")String sort,
                                       @Param("order")String order);


    /**
     * 查询 供应商产品 总数
     * @param supplierId
     * @return
     */
    @Select("select count(*) from quote_detail where quote_id in (select id from quote where supplier_id = #{id})")
    Integer selectTotalCountBySupplierId(@Param("id") Integer supplierId);
}