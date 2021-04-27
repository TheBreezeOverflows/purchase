package com.turing.purchase.controller;

import com.turing.purchase.entity.EasyUIDataGridJsonEntity;
import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.Supplier;
import com.turing.purchase.service.SupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/supplyman")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 获取供应商基本信息，存入session中
     * @param session 会话域
     * @return 跳转供应商信息展示页面
     */
    @GetMapping("/getSupplier")
    public String getSupplier(HttpSession session){
        Supplier supplier = supplierService.getSupplierInfo((String) SecurityUtils.getSubject().getPrincipal());
        if(supplier == null){
            supplier = new Supplier();
        }
        session.setAttribute("supplier",supplier);
        return "redirect:/supplymanLook";
    }

    /**
     * 获取当前供应商的登录名
     * @return 登录名的json格式
     */
    @GetMapping("/getLoginId")
    @ResponseBody
    public String getLoginId(){
        String loginId = (String)SecurityUtils.getSubject().getPrincipal();
        return loginId;
    }

    /**
     * 获取供应商产品 EasyUI 的 DataGrid 的 JSON 格式
     * @param pageNum 当前页
     * @param pageSize 每页显示数
     * @param sort 排序的列名
     * @param order 排序方式
     * @return 供应商产品 封装后的json格式（用于RasyUI的datagrid加载数据）
     */
    @RequestMapping("/getSupplierProductsPage")
    @ResponseBody
    public EasyUIDataGridJsonEntity getSupplierProductsPage(
            @RequestParam(value = "page",required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(value = "rows",required = true,defaultValue = "10")Integer pageSize,
            @RequestParam(value = "sort",defaultValue = "") String sort,
            @RequestParam(value = "order",defaultValue = "") String order,
            @RequestParam(value = "materialCode",defaultValue = "") String materialCode,
            @RequestParam(value = "materialName",defaultValue = "")String materialName){

        EasyUIDataGridJsonEntity daraGrid = supplierService.getSupplierProductsDataGrid( pageNum,
                pageSize,
                sort,
                order,materialCode,materialName);

        return daraGrid;
    }

    /**
     * 获取供应商产品 “类别” EasyUI 的 DataGrid 的 JSON 格式
     * @param pageNum 当前页
     * @param pageSize 每页显示数
     * @param sort 排序的列名
     * @param order 排序方式
     * @return 供应商产品 “类别” 封装后的json格式（用于RasyUI的datagrid加载数据）
     */
    @RequestMapping("/getSupplierProductsTypePage")
    @ResponseBody
    public EasyUIDataGridJsonEntity getSupplierProductsTypePage(
            @RequestParam(value = "page",required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(value = "rows",required = true,defaultValue = "10")Integer pageSize,
            @RequestParam(value = "sort",defaultValue = "") String sort,
            @RequestParam(value = "order",defaultValue = "") String order){

        EasyUIDataGridJsonEntity daraGrid = supplierService.getSupplierProductsTypeDataGrid( pageNum, pageSize, sort, order);

        return daraGrid;
    }

    /**
     * 获取供应商产品 EasyUI 的 DataGrid 的 JSON 格式
     * @param pageNum 当前页
     * @param pageSize 每页显示数
     * @param sort 排序的列名
     * @param order 排序方式
     * @return 供应商产品  封装后的json格式（用于RasyUI的datagrid加载数据）
     */
    @RequestMapping("/getQuotePage")
    @ResponseBody
    public EasyUIDataGridJsonEntity getQuotePage(
            @RequestParam(value = "page",required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(value = "rows",required = true,defaultValue = "10")Integer pageSize,
            @RequestParam(value = "sort",defaultValue = "") String sort,
            @RequestParam(value = "order",defaultValue = "") String order){

        EasyUIDataGridJsonEntity daraGrid = supplierService.getQuoteDataGrid( pageNum, pageSize, sort, order);

        return daraGrid;
    }


    /**
     * 添加供应商产品细节信息
     * @param quoteDetail
     * @return
     */
    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(QuoteDetail quoteDetail){
        int i = supplierService.insertQuoteDetail(quoteDetail);
        return i>0?"success":"";
    }

    /**
     * 修改供应商产品细节信息
     * @param quoteDetail
     * @return
     */
    @PostMapping("/updateProduct")
    @ResponseBody
    public String updateProduct(QuoteDetail quoteDetail){
        int i = supplierService.updateQuoteDetail(quoteDetail);
        return i>0?"success":"";
    }

    /**
     * 删除供应商产品细节信息（包括供应商产品）
     * @param ids 供应商产品细节id数组字符串
     *            例如1,2,3,4,5（切割成数组使用）
     * @return
     */
    @PostMapping("/deleteProducts")
    @ResponseBody
    public String deleteProducts(String ids){
        String[] split = ids.split(",");
        int i = supplierService.deleteQuoteDetails(split);
        return i>0?"success":"";
    }

    /**
     * 添加供应商产品类别
     * @return
     */
    @PostMapping("/addProductType")
    @ResponseBody
    public String addProductType(Material material){
        int i = supplierService.insertMaterial(material);
        return i>0?"success":"";
    }

    /**
     * 修改供应商产品类别
     * @param material
     * @return
     */
    @PostMapping("/updateProductType")
    @ResponseBody
    public String updateProductType(Material material){
        int i = supplierService.updateMaterial(material);
        return i>0?"success":"";
    }

}
