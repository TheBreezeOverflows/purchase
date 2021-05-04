package com.turing.purchase.service.impl;

import com.turing.purchase.entity.*;
import com.turing.purchase.mapper.*;
import com.turing.purchase.service.SupplierService;
import com.turing.purchase.util.HandleTool;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired(required = false)
    private SupplierMapper supplierMapper;
    @Autowired(required = false)
    private QuoteMapper quoteMapper;
    @Autowired(required = false)
    private QuoteDetailMapper quoteDetailMapper;
    @Autowired(required = false)
    private MaterialMapper materialMapper;
    @Autowired(required = false)
    private SuppMaterialMapper suppMaterialMapper;
    //流水号
    public static int reverNum = 1;

    /**
     * 检查reverNum的合法性，
     * 请在每次改变reverNum后检查一次
     */
    private void checkReverNum(){
        if (reverNum>=100000)reverNum=1;
    }

    /**
     * 获取供应商基本信息
     * @param userName 用户名
     * @return 供应商基本信息
     */
    @Override
    public Supplier getSupplierInfo(String userName) {
        Supplier supplier = supplierMapper.selectByUserName(userName);
        supplier.setStatus(HandleTool.getAllNoStr(supplier.getStatus()));
        supplier.setKind(HandleTool.getAllNoStr(supplier.getKind()));
        return supplier;
    }

    /**
     *
     * @param  id 供应商id
     * @return 供应商对象
     */
    @Override
    public Supplier getSupplierInfobyId(long id) {
        SupplierExample example =new SupplierExample();
        SupplierExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Supplier> suppliers = supplierMapper.selectByExample(example);
        if (suppliers.size()>0){
            return suppliers.get(0);
        }
        return null;
    }


    /**
     *
     * @param materid 产品信息id
     * @return 供应商id集合
     */
    @Override
    public List<Long> getSupplierMaterInfoId(long materid) {
        SuppMaterialExample example=new SuppMaterialExample();
        SuppMaterialExample.Criteria criteria = example.createCriteria();
        criteria.andMaterialIdEqualTo(materid);
        //根据物资信息id获取供应商id集合
        List<SuppMaterial> suppMaterials = suppMaterialMapper.selectByExample(example);
        if (suppMaterials.size()>0){
            List<Long> supmaterbyid= new ArrayList<>();
            //遍历获取到的供应商集合
            for (SuppMaterial supmater:suppMaterials) {
                supmaterbyid.add(supmater.getSupplierId());
            }
            return supmaterbyid;
        }
        return null;
    }

    /**
     * 获取当前用户的供应商id
     * @return 当前用户的供应商id
     */
    private int getSupplierId(){
        return supplierMapper.selectIdByUserName(SecurityUtils.getSubject().getPrincipal().toString());
    }

    /**
     * 获取供应商产品细节 并分页、排序
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @param sort 排序的列名
     * @param order 排序方式 asc或desc
     * @return 封装后的实体（用于转换成datagrid的json格式）
     */
    @Override
    public EasyUIDataGridJsonEntity getSupplierProductsDataGrid(Integer pageNum,
                                                                Integer pageSize,
                                                                String sort,
                                                                String order,
                                                                String materialCode,
                                                                String materialName) {
        //获取supplierId
        Integer id = getSupplierId();
        //获取供应商产品列表（分页，排序）
        List<QuoteDetail> list = quoteDetailMapper.selectBySupplierId( id ,
                (pageNum-1)*pageSize,
                pageSize,
                HandleTool.toChangeStr(sort),
                order,
                "%"+materialCode+"%",
                "%"+materialName+"%");

        //封装成datagrid格式实体
        EasyUIDataGridJsonEntity easyui = new EasyUIDataGridJsonEntity();
        easyui.setTotal(getTotalProducts());
        easyui.setRows(list);
        //返回封装后的实体
        return easyui;
    }

    /**
     * 查询供应商产品细节的总数量（用于datagrid的分页格式实体）
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
     * @return 供应商产品类别的总数目
     */
    @Override
    public int getTotalProductsType() {
        return materialMapper.selectTotalPageBySupplierId(getSupplierId());
    }


    /**
     * 获取对应供应商的报价信息 分页、排序
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @param sort 排序的列名
     * @param order 排序方式
     * @return 封装后的实体（用于转换成datagrid的json格式）
     */
    @Override
    public EasyUIDataGridJsonEntity getQuoteDataGrid(Integer pageNum, Integer pageSize, String sort, String order) {
        //获取supplierId
        Integer id = getSupplierId();
        //获取供应商产品列表（分页，排序）
        List<Quote> list = quoteMapper.selectQuoteAndStockSupplier(id,(pageNum-1)*pageSize,pageSize,sort,order
        );
        //封装成datagrid格式实体
        EasyUIDataGridJsonEntity easyui = new EasyUIDataGridJsonEntity();
        easyui.setTotal(getTotalQuote());
        easyui.setRows(list);
        //返回封装后的实体
        return easyui;
    }

    /**
     * 获取对应供应商id 的报价总数
     * @return 对应供应商id 的报价总数
     */
    @Override
    @Transactional
    public int getTotalQuote() {
        return quoteMapper.selectCount(getSupplierId());
    }

    /**
     * 添加供应商产品细节
     * @param quoteDetail
     * @return
     */
    @Override
    @Transactional
    public int insertQuoteDetail(QuoteDetail quoteDetail) {
        //各属性
        Double count = Double.parseDouble(quoteDetail.getAmount());//数量
        Double price = quoteDetail.getUnitPrice().doubleValue();//价格
        Double mixPrice = quoteDetail.getMixPrice()==null?0:quoteDetail.getMixPrice().doubleValue();//运杂费
        Double otherPrice = quoteDetail.getOtherPrice()==null?0:quoteDetail.getOtherPrice().doubleValue();//其他费
        Double sumPrice = count*price;
        Double totalPrice = count*price+mixPrice+otherPrice;
        //获取当前供应商基本信息
        Supplier supplier = supplierMapper.selectByUserName(SecurityUtils.getSubject().getPrincipal().toString());

        //获取供应商id
        int supplierId = getSupplierId();

        //添加供应商产品
        QuoteWithBLOBs quote = new QuoteWithBLOBs();
            //获取流水号
        String number = HandleTool.getReverNumber("500", reverNum);
            //检查流水号正确性
        checkReverNum();
        quote.setQuoteNum(number);
        quote.setSupplierId((long) supplierId);
        quote.setQuoCompany(supplier.getCompany());
        quote.setQuoAddress(supplier.getAddress());
        quote.setQuoLinkman(supplier.getContact());
        quote.setQuoPhone(supplier.getPhone());
        quote.setQuoFax(supplier.getFax());
        quote.setQuoEmail(supplier.getEmail());
        quote.setSumAmount(BigDecimal.valueOf(count));
        quote.setOverallPrice(BigDecimal.valueOf(totalPrice));
        quote.setQueTitle("对"+number+"的报价");
        quote.setStatus("B001-1");
        int i = quoteMapper.insertSelectiveReturnId(quote);
        //添加供应商产品细节
            //设置值
        quoteDetail.setQuoteId(quote.getMyID());
        quoteDetail.setSumPrice(BigDecimal.valueOf(sumPrice));
        quoteDetail.setTotalPrice(BigDecimal.valueOf(totalPrice));
        int i1 = quoteDetailMapper.insertSelective(quoteDetail);
        return i+i1;
    }

    /**
     * 修改供应商产品细节
     * @param quoteDetail
     * @return
     */
    @Override
    @Transactional
    public int updateQuoteDetail(QuoteDetail quoteDetail) {
        //各属性
        Double count = Double.parseDouble(quoteDetail.getAmount());//数量
        Double price = quoteDetail.getUnitPrice().doubleValue();//价格
        Double mixPrice = quoteDetail.getMixPrice()==null?0:quoteDetail.getMixPrice().doubleValue();//运杂费
        Double otherPrice = quoteDetail.getOtherPrice()==null?0:quoteDetail.getOtherPrice().doubleValue();//其他费
        //设置值
        quoteDetail.setSumPrice(BigDecimal.valueOf(count*price));
        quoteDetail.setTotalPrice(BigDecimal.valueOf(count*price+mixPrice+otherPrice));
        //条件
        QuoteDetailExample example = new QuoteDetailExample();
        example.createCriteria().andIdEqualTo(quoteDetail.getId());
        int i = quoteDetailMapper.updateByExampleSelective(quoteDetail, example);
        return i;
    }

    /**
     * 删除供应商产品细节
     * @param quotedetailId
     * @return
     */
    @Override
    @Transactional
    public int deleteQuoteDetail(Integer quotedetailId) {
        QuoteDetailExample example = new QuoteDetailExample();
        example.createCriteria().andIdEqualTo(quotedetailId.longValue());
        int i = quoteDetailMapper.deleteByExample(example);
        return i;
    }

    /**
     * 批量删除供应商产品细节
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public int deleteQuoteDetails(String[] ids) {
        int result = 0;
        for (String id : ids){
            int i = Integer.parseInt(id);
            //查询对应的供应商产品id
            Integer quoteId = quoteDetailMapper.selectQuoteIdByQuoteDetailId(i);

            //删除供应商产品
            QuoteExample quoteExample = new QuoteExample();
            quoteExample.createCriteria().andIdEqualTo((long)quoteId);
            result += quoteMapper.deleteByExample(quoteExample);

            //删除供应商产品细节
            QuoteDetailExample example = new QuoteDetailExample();
            example.createCriteria().andIdEqualTo((long)i);
            result += quoteDetailMapper.deleteByExample(example);
        }
        return result;
    }

    /**
     * 添加产品类别
     * @param material
     * @return
     */
    @Override
    @Transactional
    public int insertMaterial(Material material) {
        //添加 返回id
        int i = materialMapper.insertSelectiveReturnId(material);
        //获取供应商id
        int supplierId = getSupplierId();
        //添加供应商产品类别表
        SuppMaterial suppMaterial = new SuppMaterial();
        suppMaterial.setSupplierId((long)supplierId);
        suppMaterial.setMaterialId(material.getId());
        int i1 = suppMaterialMapper.insertSelective(suppMaterial);
        return i+i1;
    }

    /**
     * 修改产品类别
     * @param material
     * @return
     */
    @Override
    @Transactional
    public int updateMaterial(Material material) {
        MaterialExample example = new MaterialExample();
        example.createCriteria().andIdEqualTo(material.getId());
        int i = materialMapper.updateByExampleSelective(material, example);
        return i;
    }

    /**
     * 删除产品类别
     * @param materialId
     * @return
     */
    @Override
    @Transactional
    public int deleteMaterial(long materialId) {
        //删除材料表
        MaterialExample materialExample = new MaterialExample();
        materialExample.createCriteria().andIdEqualTo(materialId);
        int i = materialMapper.deleteByExample(materialExample);
        //删除供应商_材料表
        SuppMaterialExample suppMaterialExample = new SuppMaterialExample();
        suppMaterialExample.createCriteria().andMaterialIdEqualTo(materialId);
        int i1 = suppMaterialMapper.deleteByExample(suppMaterialExample);
        return i+i1;
    }

    /**
     * 添加供应商产品
     * @param quote
     * @return
     */
    @Override
    @Transactional
    public int insertQuote(QuoteWithBLOBs quote) {
        int i = quoteMapper.insertSelective(quote);
        return i;
    }
}
