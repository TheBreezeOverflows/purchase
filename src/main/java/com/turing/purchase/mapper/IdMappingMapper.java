package com.turing.purchase.mapper;

import com.turing.purchase.entity.IdMapping;
import com.turing.purchase.entity.IdMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdMappingMapper {
    int countByExample(IdMappingExample example);

    int deleteByExample(IdMappingExample example);

    int insert(IdMapping record);

    int insertSelective(IdMapping record);

    List<IdMapping> selectByExample(IdMappingExample example);

    int updateByExampleSelective(@Param("record") IdMapping record, @Param("example") IdMappingExample example);

    int updateByExample(@Param("record") IdMapping record, @Param("example") IdMappingExample example);
}