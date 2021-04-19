package com.turing.purchase.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.purchase.entity.EasyUIDataGridJsonEntity;
import com.turing.purchase.entity.QuoteDetail;
import com.turing.purchase.entity.Supplier;
import com.turing.purchase.service.SupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/supplyman")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getSupplier")
    public String getSupplier(HttpSession session){
        Supplier supplier = supplierService.getSupplierInfo((String) SecurityUtils.getSubject().getPrincipal());
        session.setAttribute("supplier",supplier);
        return "redirect:/supplymanLook";
    }

    @GetMapping("/getLoginId")
    @ResponseBody
    public String getLoginId(){
        String loginId = (String)SecurityUtils.getSubject().getPrincipal();
        return loginId;
    }

    /*@RequestMapping("/getSupplierProductsPage")
    public String getSupplierProductsPage(
            @RequestParam(value = "pageNum",required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize",required = true,defaultValue = "5")Integer pageSize,
            HttpSession session){
        PageHelper.startPage(pageNum,pageSize);
        List<QuoteDetail> products = supplierService.getSupplierProducts((String) SecurityUtils.getSubject().getPrincipal());
        PageInfo<QuoteDetail> pageInfo = new PageInfo<>(products);
        session.setAttribute("pageInfo",pageInfo);
        return "redirect:/goodsSelect";
    }*/
    @RequestMapping("/getSupplierProductsPage")
    @ResponseBody
    public EasyUIDataGridJsonEntity getSupplierProductsPage(
            @RequestParam(value = "page",required = true,defaultValue = "1")Integer pageNum,
            @RequestParam(value = "rows",required = true,defaultValue = "10")Integer pageSize,
            @RequestParam(value = "sort",defaultValue = "") String sort,
            @RequestParam(value = "order",defaultValue = "") String order){
        EasyUIDataGridJsonEntity daraGrid = supplierService.getSupplierProductsDaraGrid((String) SecurityUtils.getSubject().getPrincipal(), pageNum, pageSize, sort, order);

        return daraGrid;
    }

}
