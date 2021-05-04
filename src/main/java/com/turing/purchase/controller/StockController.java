package com.turing.purchase.controller;

import com.turing.purchase.entity.*;
import com.turing.purchase.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 采购计划管理控制层
 */
@Controller
@RequestMapping("planStock")
public class StockController {
    //ser依赖添加
    //物资信息ser依赖
    @Autowired
    private PlanDemandEnteringServer planDemandEnteringServer;
    //需求计划表ser依赖
    @Autowired
    private PlanOrdersService planOrdersService;
    //供应商信息ser依赖
    @Autowired
    private SupplierService supplierService;
    //采购计划依赖添加
    @Autowired
    private StockService stockService;
    //编号对照表
    @Autowired
    private IdMappingService idMappingService;

    //添加采购计划
    @GetMapping("/insertStockMessage")
    @ResponseBody
    public boolean insertStockMessage(String planStockNumber,String planStockName, String planStockWrite,
                                      String planStockBudget,String palnStockDate,String stocktype,
                                      String stocksel,HttpSession session){
        boolean fal =false;
        // 时间格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        //创建采购计划对象
        StockWithBLOBs stblbs =new StockWithBLOBs();
        stblbs.setStockNum(planStockNumber);//添加采购计划编号
        stblbs.setStockName(planStockName);//添加采购计划姓名
        //获取当前登录用户id
        SysUsers sysUsers =(SysUsers)session.getAttribute("SysUsers");
        stblbs.setAuthorId(sysUsers.getId()+"");//添加编制人id
        stblbs.setAuthor(planStockWrite);//添加编制人
        //添加预算
        BigDecimal bd=new BigDecimal(planStockBudget);
        bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        stblbs.setBudget(bd);
        try {
            //日期减一
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(sdf.parse(palnStockDate));
            rightNow.add(Calendar.DAY_OF_MONTH, -1);
            Date dt1 = rightNow.getTime();
            stblbs.setEndDate(dt1);//计划报价截止
        } catch (ParseException e) {
            System.out.println("日期类型转换异常");
            e.printStackTrace();
        }
        stblbs.setStockType(stocktype);//采购类型
        int in = stockService.insesrtStock(stblbs);
        if (in>0){
            fal=true;
        }
        //添加编号对照表的信息
        IdMapping idm=new IdMapping();
        idm.setStockId((long)in);//添加采购编号
        Orders purchasOrder =(Orders)session.getAttribute("PurchaseWriteOrderMessage");
        idm.setOrderId(purchasOrder.getId());//添加物资编号
        idm.setStatus("C001-20");//添加状态
        boolean ble = idMappingService.insertStockId(idm);
        //判断是否是定向求购
        if (!"".equals(stocksel)){
            System.out.println("stock="+stocksel);
            //将截取到的id字符转换为数组
            String[] split = stocksel.split(",");
            //循环添加定向求购的供应商
            for (String str:split){
                System.out.println("in="+in+"str="+str);
                stockService.StocksupplierById(in,Long.parseLong(str));
            }
            return true;
        }
        return  fal;
    }

    //页面未下达初始查询
    @GetMapping("/StockSelectNoDate")
    public String StockSelectNoDate(HttpSession session){
        //查询所有采购计划
        List<Stock> stocks = stockService.FinAllSotck();
        //接收未下达的采购计划
        List<Stock> stock=new ArrayList<>();
        for (Stock stk:stocks) {
            if (stk.getSubmitDate()==null){
                stock.add(stk);
            }
        }
        session.setAttribute("StockNoSubmit",stock);
        return "redirect:/planman/Project_list4";
    }

    //初始编制采购计划页面跳转
    @GetMapping("/StockDetailGeneral")
    @ResponseBody
    public  boolean StockDetailGeneral(Integer id,HttpSession session){
        //根据采购id查询采购信息
        Stock stock = stockService.FinAllSotckById(id);
        //根据采购id查询编号对照表中关系id
        IdMapping idMapping = idMappingService.selectStockbyid(stock.getId());
        //根据采购表对应的需求计划id获取需求计划信息
        Orders orders = planOrdersService.FinbyOrder(idMapping.getOrderId());
        if (orders!=null){
            //当前单价
            BigDecimal unitPrice = orders.getUnitPrice();
            //数量转为bigdeciml类型
            BigDecimal   inamount   = new BigDecimal(orders.getAmount());
            unitPrice=inamount.multiply(unitPrice);
            orders.setSumPrice(unitPrice);
        }
        //准备一个初始的供应商id集合
        List<Long> supplierMaterInfoId=new ArrayList<>();
        //判断是否是定向询价的
        if(stock.getStockType()=="2") {
            //查询定向询价的供应商
            supplierMaterInfoId=stockService.FinAllStocksupplier(stock.getId());
        }else {
            //根据物资编号查询物资信息
            Material material = planDemandEnteringServer.FinSingleMaterCode(orders.getMaterialCode());
            //判断查询到的物资信息是否为空
            if (material != null) {
                //根据物资信息id获取有此物资信息的供应商表id集合
                 supplierMaterInfoId = supplierService.getSupplierMaterInfoId(material.getId());
            }
        }
        //判断查询到的供应商id是否为空
        if (supplierMaterInfoId != null) {
            //设置一个初始供应商集合对象存储根据id获取到的供应商
            List<Supplier> supplier = new ArrayList<>();
            //根据供应商表id集合查询供应商信息
            for (Long log : supplierMaterInfoId) {
                Supplier supplierInfo = supplierService.getSupplierInfobyId(log);
                if (supplierInfo != null) {
                    //判断根据id查询到的供应商不为空，放入初始设置的集合对象中
                    supplier.add(supplierInfo);
                }
            }
            session.setAttribute("PurchaseWriteSupplierName", supplier);
        } else {
            session.setAttribute("PurchaseWriteSupplierName", null);
        }
        session.setAttribute("StockMessage",stock);//添加session的采购计划信息
        session.setAttribute("PurchaseWriteOrderMessage",orders);//添加session的需求计划信息
        return true;
    }

    //采购计划下达
    @GetMapping("/StockTransmit")
    @ResponseBody
    public boolean StockTransmit(Integer id,HttpSession session){
        boolean fal=false;
        //判断部长是否同意S002-1
        //根据采购id查询采购信息
        Stock stock = stockService.FinAllSotckById(id);
        String leadAgree = stock.getLeadAgree();//获取部长对此采购是否同意
        System.out.println(leadAgree);
        if ("S002-1".equals(leadAgree)){
            //添加下达时间
            StockWithBLOBs stocks=new StockWithBLOBs();
            stocks.setId(stock.getId());
            stocks.setStockNum(stock.getStockNum());
            stocks.setStockName(stock.getStockName());
            stocks.setAuthorId(stock.getAuthorId());
            stocks.setAuthor(stock.getAuthor());
            stocks.setStockType(stock.getStockType());
            stocks.setBudget(stock.getBudget());
            stocks.setEndDate(stock.getEndDate());
            stocks.setSubmitDate(new Date());
            stocks.setLeadAgree(stock.getLeadAgree());
            stocks.setLeadDate(stock.getLeadDate());
            stocks.setLeader(stock.getLeader());
            stocks.setLeaderId(stock.getLeaderId());
            boolean fla = stockService.updateStock(stocks);
            if (fla){
                System.out.println("ok");
                //修改编号对照表C001-60：未编询价书
                //根据采购id查询编号对照表中关系id
                IdMapping idMapping = idMappingService.selectStockbyid(stock.getId());
                idMapping.setStatus("C001-60");
                boolean blean = idMappingService.UpdateStockStatus(idMapping);
                if (blean){
                    fal=true;
                }
            }
        }else {
            fal=false;
        }
        return fal;
    }
}
