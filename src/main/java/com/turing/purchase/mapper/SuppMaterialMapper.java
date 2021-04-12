package com.turing.purchase.mapper;

import com.turing.purchase.entity.SuppMaterial;
import com.turing.purchase.entity.SuppMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuppMaterialMapper {
    int countByExample(SuppMaterialExample example);

    int deleteByExample(SuppMaterialExample example);

    int insert(SuppMaterial record);

    int insertSelective(SuppMaterial record);

    List<SuppMaterial> selectByExample(SuppMaterialExample example);

    int updateByExampleSelective(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);

    int updateByExample(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);
}