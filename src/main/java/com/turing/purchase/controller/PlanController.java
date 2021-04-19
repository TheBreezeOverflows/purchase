package com.turing.purchase.controller;

/**
 * 计划员控制层
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.Orders;
import com.turing.purchase.service.PlanDemandEnteringServer;
import com.turing.purchase.service.PlanOrdersService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 计划员控制层
 */
@Controller
@RequestMapping("plan")
public class PlanController {
    //物资ser依赖
    @Autowired
    private PlanDemandEnteringServer planDemandEnteringServer;

    //需求计划表ser依赖
    @Autowired
    private PlanOrdersService planOrdersService;
    //需求计划录入初始分页进入
    @GetMapping("/pclassSelect")
    public String planPclass(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpSession session){
        planPclassPage(pn,session);
        return "redirect:/planman/pclass_select";
    }
    //需求计划录入的初始分页查询
    @GetMapping("/pclassSelects")
    @ResponseBody
    public boolean planPclassPaging(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpSession session){
        //从第几页开始查，每页有几条数据,pn默认为1
        if (pn>0){
            planPclassPage(pn,session);
            return true;
        }
        return false;
    }

    /**
     * 分页查询的调用分页方法
     * @param pn    查询第几页
     * @param session session对象
     */
    public void planPclassPage(Integer pn, HttpSession session){

    PageHelper.startPage(pn,5);
    //获取所有物资信息
    List<Material> materials = planDemandEnteringServer.FinAllMaterials();
    //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
    PageInfo<Material> info = new PageInfo<>(materials);
    session.setAttribute("pageInfo",info);
    //info.getList()可以查看当前info的所有信息
    }


    //根据指定id查询物资信息集合
    @GetMapping("/EntryPageSkip")
    @ResponseBody
    public  boolean EntryPageSkip(String str, HttpSession session){
        System.out.println("进入录入物资页面");
        //判断获取到的选中集合是否为空
        if (!"".equals(str)){
            //从第几页开始查，每页有几条数据;
            PageHelper.startPage(1,3);
            //设置一个Material类型的list集合
            List<Material> mater =new ArrayList<Material>();
            //根据id获取所有选中的物资信息
            //将str的最后一个','截取掉
            str=str.substring(0,str.length()-1);
            //将截取到的id字符转换为数组
            String[] split = str.split(",");
            //遍历id查询物资信息
            for (String s:split) {
                Material material = planDemandEnteringServer.FinSingleMaterials(Integer.parseInt(s));
                mater.add(material);
            }
            //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
            PageInfo<Material> info = new PageInfo<>(mater);
            session.setAttribute("SkipEntryPageInfo",info);
            return true;
        }
            return false;

    }

    //保存
    @GetMapping("/saveplan")
    @ResponseBody
    public boolean save(String MaterialsCoding,String MaterialsName,String MaterialsQuantity,String MaterialsUnit,
                        String MaterialsMoney,String MaterialsData,String MaterialsRemark){
        System.out.println("进入保存的控制层");
        System.out.println("物资编码："+MaterialsCoding+"物资名："+MaterialsName+"数量："+MaterialsQuantity+"计量单位："+MaterialsUnit+
                "单价："+MaterialsMoney+"日期"+MaterialsData+"备注："+MaterialsRemark);
        // 时间格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        Orders or=new Orders();
        //设置参数
        or.setMaterialCode(MaterialsCoding);//物资编码
        or.setMaterialName(MaterialsCoding);//物资名称
        or.setAmount(MaterialsQuantity);//数量
        or.setMeasureUnit(MaterialsUnit);//计量单位
        //单价
        BigDecimal bd=new BigDecimal(MaterialsMoney);
        bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        or.setUnitPrice(bd);
        try {
            //开始交货日期
            or.setStartDate(sdf.parse(MaterialsData));
        } catch (ParseException e) {
            System.out.println("日期类型转换异常");
            e.printStackTrace();
        }
        or.setRemark(MaterialsRemark);//备注
        boolean fal = planOrdersService.insertOreder(or);
        if (fal){
            return true;
        }
        return false;
    }








}
