package com.turing.purchase.mapper;

import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.MaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MaterialMapper {
    int countByExample(MaterialExample example);

    int deleteByExample(MaterialExample example);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    //自定义方法
    List<Material> selectBySupplierIdPage(@Param("id") Integer supplierId,
                                        @Param("pageNum") Integer pageNum,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("sort")String sort,
                                        @Param("order")String order);

    @Select("select count(*) from material where id in (select material_id from supp_material where supplier_id = #{id})")
    Integer selectTotalPageBySupplierId(@Param("id")Integer supplierId);

    /**
     * 插入一条产品记录
     * @param material 产品记录对象
     * @return 插入成功的主键id
     */
    int insertSelectiveReturnId(Material material);

}