package com.turing.purchase.service;

import com.turing.purchase.entity.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SupplierService {

    /**
     * 获取供应商信息
     *
     * @param userName 用户名
     * @return 供应商对象
     */
    Supplier getSupplierInfo(String userName);

    /**
     * 根据供应商id
     * @param  id 供应商id
     * @return 供应商对象
     */
    Supplier getSupplierInfobyId(long id);

    /**
     *  根据产品信息id获取有此产品的供应商id集合
     * @param materid 产品信息id
     * @return 供应商id集合
     */
    List<Long> getSupplierMaterInfoId(long materid);

    //查询供应商产品细节（datagrid）
    EasyUIDataGridJsonEntity getSupplierProductsDataGrid(Integer pageNum,Integer pageSize,String sort,String order,
                                                                String materialCode,
                                                         String materialName);

    //获取供应商产品细节总条数
    int getTotalProducts();

    //查询供应商产品类别（datagrid）
    EasyUIDataGridJsonEntity getSupplierProductsTypeDataGrid(Integer pageNum,Integer pageSize,String sort,String order);

    //获取供应商产品类别条数
    int getTotalProductsType();

    //查询供应商产品（datagrid）
    EasyUIDataGridJsonEntity getQuoteDataGrid(Integer pageNum,Integer pageSize,String sort,String order);

    //获取供应商产品条数
    int getTotalQuote();

    //添加供应商产品细节
    int insertQuoteDetail(QuoteDetail quoteDetail);

    //修改供应商产品细节
    int updateQuoteDetail(QuoteDetail quoteDetail);

    //删除供应商产品细节
    int deleteQuoteDetail(Integer quotedetailId);

    //批量删除供应商产品细节
    int deleteQuoteDetails(String[] ids);

    //添加产品类别
    int insertMaterial(Material material);

    //修改产品类别
    int updateMaterial(Material material);

    //删除产品类别
    int deleteMaterial(long materialId);

    //添加供应商产品
    int insertQuote(QuoteWithBLOBs quote);

}
