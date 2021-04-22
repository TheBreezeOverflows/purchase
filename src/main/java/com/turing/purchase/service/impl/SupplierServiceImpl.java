package com.turing.purchase.service.impl;

import com.turing.purchase.entity.EasyUIDataGridJsonEntity;
import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.Supplier;
import com.turing.purchase.mapper.MaterialMapper;
import com.turing.purchase.mapper.QuoteDetailMapper;
import com.turing.purchase.mapper.SupplierMapper;
import com.turing.purchase.service.SupplierService;
import com.turing.purchase.util.HandleTool;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired(required = false)
    private SupplierMapper supplierMapper;
    @Autowired(required = false)
    private QuoteDetailMapper quoteDetailMapper;
    @Autowired(required = false)
    private MaterialMapper materialMapper;

    /**
     * 获取供应商基本信息
     * @param userName 用户名
     * @return
     */
    @Override
    public Supplier getSupplierInfo(String userName) {
        Supplier supplier = supplierMapper.selectByUserName(userName);
        supplier.setStatus(HandleTool.getAllNoStr(supplier.getStatus()));
        supplier.setKind(HandleTool.getAllNoStr(supplier.getKind()));
        return supplier;
    }

    /**
     * 获取当前用户的供应商id
     * @return
     */
    private int getSupplierId(){
        return supplierMapper.selectIdByUserName(SecurityUtils.getSubject().getPrincipal().toString());
    }

    /**
     * 获取供应商产品 并分页、排序
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @param sort 排序的列名
     * @param order 排序方式 asc或desc
     * @return 封装后的实体（用于转换成datagrid的json格式）
     */
    @Override
    public EasyUIDataGridJsonEntity getSupplierProductsDataGrid(Integer pageNum,Integer pageSize,String sort,String order) {
        //获取supplierId
        Integer id = getSupplierId();
        //获取供应商产品列表（分页，排序）
        List<QuoteDetail> list = quoteDetailMapper.selectBySupplierId(id,(pageNum-1)*pageSize,
                pageSize,HandleTool.toChangeStr(sort),order);
        //封装成datagrid格式实体
        EasyUIDataGridJsonEntity easyui = new EasyUIDataGridJsonEntity();
        easyui.setTotal(getTotalProducts());
        easyui.setRows(list);
        //返回封装后的实体
        return easyui;
    }

    /**
     * 查询供应商产品的总数量（用于datagrid的分页格式实体）
     * @return 供应商产品总数
     */
    @Override
    public int getTotalProducts() {
        return quoteDetailMapper.selectTotalCountBySupplierId(getSupplierId());
    }

    /**
     * 获取供应商产品类别 并分页、排序
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @param sort 排序的列名
     * @param order 排序方式
     * @return 封装后的实体（用于转换成datagrid的json格式）
     */
    @Override
    public EasyUIDataGridJsonEntity getSupplierProductsTypeDataGrid(Integer pageNum, Integer pageSize, String sort, String order) {
        //获取supplierId
        Integer id = getSupplierId();
        //获取供应商产品类别列表（分页，排序）
        List<Material> list = materialMapper.selectBySupplierIdPage(id, (pageNum - 1) * pageSize,
                pageSize, HandleTool.toChangeStr(sort), order);
        //修改materialType为字符
        for(Material m : list){
            m.setMaterialType(HandleTool.getAllNoStr(m.getMaterialType()));
        }
        //封装成datagrid格式实体
        EasyUIDataGridJsonEntity easyui = new EasyUIDataGridJsonEntity();
        easyui.setTotal(getTotalProductsType());
        easyui.setRows(list);
        //返回封装后的实体
        return easyui;
    }

    /**
     * 查询供应商产品类别的总数目
     * @return
     */
    @Override
    public int getTotalProductsType() {
        return materialMapper.selectTotalPageBySupplierId(getSupplierId());
    }
}
