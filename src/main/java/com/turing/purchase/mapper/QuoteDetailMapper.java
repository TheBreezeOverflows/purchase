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
//    @Select("select * from quote_detail where quote_id in (select id from quote where supplier_id = (select id from sys_users where LOGIN_ID = #{userName}))")
    List<QuoteDetail> selectByUserName(@Param("userName") String userName,
                                       @Param("pageNum") Integer pageNum,
                                       @Param("pageSize") Integer pageSize,
                                       @Param("sort")String sort,
                                       @Param("order")String order);

    @Select("select count(*) from quote_detail where quote_id in (select id from quote where supplier_id = (select id from sys_users where LOGIN_ID = #{userName}))")
    Integer selectTotalCountByUserName(@Param("userName") String userName);
}