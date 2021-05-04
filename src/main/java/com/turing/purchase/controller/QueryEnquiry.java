package com.turing.purchase.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.purchase.entity.IdMapping;
import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.Orders;
import com.turing.purchase.entity.Stock;
import com.turing.purchase.service.IdMappingService;
import com.turing.purchase.service.PlanDemandEnteringServer;
import com.turing.purchase.service.PlanOrdersService;
import com.turing.purchase.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 询价书编制
 */
@Controller
@RequestMapping("Enquiry")
public class QueryEnquiry {

    //依赖mapper
    //物资信息ser依赖
    @Autowired
    private PlanDemandEnteringServer planDemandEnteringServer;

    //需求计划表ser依赖
    @Autowired
    private PlanOrdersService planOrdersService;

    //采购计划依赖添加
    @Autowired
    private StockService stockService;
    //编号对照表
    @Autowired
    private IdMappingService idMappingService;
    //未编制询价书一览表
    @GetMapping("/unnumberedQueryEnquiry")
    public String unnumberedQueryEnquiry(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpSession session){
        PageInfo<Stock> stockPageInfo = planPclassPage(pn, "", "", "", session);

        //设置条件初始session
        session.setAttribute("pTitle","");//采购名称
        session.setAttribute("pItem","");//物资名称
        session.setAttribute("pStocktype","");//类型

        session.setAttribute("UnnumberedQueryEnquiryPageInfo", stockPageInfo);
        return "redirect:/queryandqueto/Project_list";
    }

    //需求计划录入的分页查询
    @GetMapping("/unnumberedQueryEnquirySelects")
    @ResponseBody
    public boolean planPclassPaging(@RequestParam(value = "pn",defaultValue = "1")Integer pn,String StockName,String MaterName,String StockType,HttpSession session){
        //从第几页开始查，每页有几条数据,pn默认为1
        PageInfo<Stock> stockPageInfo = planPclassPage(pn, StockName, MaterName, StockType, session);
        //修改条件session
        session.setAttribute("pTitle",StockName);//采购名称
        session.setAttribute("pItem",MaterName);//物资名称
        session.setAttribute("pStocktype",StockType);//类型
        session.setAttribute("UnnumberedQueryEnquiryPageInfo",stockPageInfo);
        return true;
    }




    /**
     * 分页查询的调用分页方法
     * @param pn    查询第几页
     * @param session session对象
     */
    public PageInfo<Stock> planPclassPage(Integer pn,String StockName,String MaterName,String StockType,HttpSession session) {

        //接收查询到的所有未编询价书的采购计划
        List<Stock> stocklist = new ArrayList<>();
        //查询所有采购计划
        List<Stock> stockAllList = new ArrayList<>();
        //判断是初始查询还是指定查询
        if (StockName==""&&MaterName==""&&StockType==""){
            //查询所有采购计划
           stockAllList = stockService.FinAllSotck();
        }else{
            //指定查询所有采购计划
            stockAllList = stockService.FinAllSotckNameType(StockName,StockType);
        }
        //判断是否有查询物资名称
        if (MaterName!=""){
            for (Stock stck : stockAllList) {
                //判断采购计划是否下达
                if (stck.getSubmitDate() != null && "S002-1".equals(stck.getLeadAgree())) {
                    //判断采购物资名称是否为指定的
                    //根据当前采购id获取需求计划编号
                    IdMapping idMapping = idMappingService.selectStockbyid(stck.getId());
                    //根据需求计划id获取需求计划信息
                    Orders orders = planOrdersService.FinbyOrder(idMapping.getOrderId());

                    //根据物资名称查询所有有关的物资编号
                    List<Material> materials = planDemandEnteringServer.FinSingleMaterName(MaterName);
                    for (Material mater:materials) {
                        //判断根据当前采购id查询到的需求信息中的物资编号是否有与指定查询中的需求信息相同
                        if (mater.getMaterialNum().equals(orders.getMaterialCode())){
                            if ("C001-60".equals(idMapping.getStatus())) {
                                //根据采购计划id单个查询在用list<stock>接收
                                Stock stock = stockService.FinAllSotckById(idMapping.getStockId());
                                stocklist.add(stock);
                                break;
                            }
                        }
                    }
                }
            }
        }else{
            //没有查询物资名称
            for (Stock stck : stockAllList) {
                //判断采购计划是否下达
                if (stck.getSubmitDate() != null && "S002-1".equals(stck.getLeadAgree())) {
                    //判断采购物资名称是否为指定的
                    //根据当前采购id获取需求计划编号
                    IdMapping idMapping = idMappingService.selectStockbyid(stck.getId());
                    if ("C001-60".equals(idMapping.getStatus())) {
                        //根据采购计划id单个查询在用list<stock>接收
                        Stock stock = stockService.FinAllSotckById(idMapping.getStockId());
                        stocklist.add(stock);
                    }
                }
            }
        }
        //创建Page
        Page page = new Page(pn,5);
        //为Page类中的total属性赋值
        int total =stocklist.size();
        page.setTotal(total);
        //计算当前需要显示的数据下标起始值
        int startIndex = (pn - 1) * 5;
        int endIndex = Math.min(startIndex + 5,total);
        //从链表中截取需要显示的子链表，并加入到Page
        page.addAll(stocklist.subList(startIndex,endIndex));
        //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
        PageInfo<Stock> info = new PageInfo<>(page);
        //info.getList()可以查看当前info的所有信息
        return info;
    }








}
