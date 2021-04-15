package com.turing.purchase.controller;

/**
 * 计划员控制层
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.purchase.entity.Material;
import com.turing.purchase.service.PlanDemandEnteringServer;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    //需求计划录入的初始分页查询
    @GetMapping("/pclassSelect")
    public String userLogout(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpSession session){
        //从第几页开始查，每页有几条数据,pn默认为1
        PageHelper.startPage(pn,5);
        //获取所有物资信息
        List<Material> materials = planDemandEnteringServer.FinAllMaterials();
        System.out.println("物资信息集合有："+materials.size());
        //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
        PageInfo<Material> info = new PageInfo<>(materials);
        session.setAttribute("pageInfo",info);
        System.out.println(info.getList());
        return "redirect:/planman/pclass_select";
    }




}
