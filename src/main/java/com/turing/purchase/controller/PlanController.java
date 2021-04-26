package com.turing.purchase.controller;

/**
 * 计划员控制层
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.turing.purchase.entity.Material;
import com.turing.purchase.entity.Orders;
import com.turing.purchase.entity.SysUsers;
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
                        String MaterialsMoney,String MaterialsData,String MaterialsRemark,HttpSession session){
        System.out.println("进入保存的控制层");
        System.out.println("物资编码："+MaterialsCoding+"物资名："+MaterialsName+"数量："+MaterialsQuantity+"计量单位："+MaterialsUnit+
                "单价："+MaterialsMoney+"日期"+MaterialsData+"备注："+MaterialsRemark);
        // 时间格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        Orders or=new Orders();
        //设置参数
        or.setMaterialCode(MaterialsCoding);//物资编码
        or.setMaterialName(MaterialsName);//物资名称
        or.setAmount(MaterialsQuantity);//数量
        or.setMeasureUnit(MaterialsUnit);//计量单位
        //单价
        BigDecimal bd=new BigDecimal(MaterialsMoney);
        bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        or.setUnitPrice(bd);
        try {
            //开始交货日期
            or.setEndDate(sdf.parse(MaterialsData));
        } catch (ParseException e) {
            System.out.println("日期类型转换异常");
            e.printStackTrace();
        }
        SysUsers emp =(SysUsers)session.getAttribute("SysUsers");
        or.setAuthorId(emp.getId()+"");//编制人序号
        or.setAuthor(emp.getLoginId());//编制人
        or.setRemark(MaterialsRemark);//备注
        boolean fal = planOrdersService.insertOreder(or);
        if (fal){
            return true;
        }
        return false;
    }



    //需求计划录入初始分页进入
    @GetMapping("/planOrderSelect")
    public String planOrder(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpSession session){
        PageHelper.startPage(pn,5);
        //获取所有物资信息
        List<Orders> orders = planOrdersService.FinAllOrder();
        //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
        PageInfo<Orders> info = new PageInfo<>(orders);
        session.setAttribute("pageInfo",info);
        //info.getList()//可以查看当前info的所有信息
        return "redirect:/planman/Order_ytb_list";
    }


    //需求计划录入的条件分页查询
    @GetMapping("/planOrderSelects")
    @ResponseBody
    public String planOrders(@RequestParam(value = "pn",defaultValue = "1")Integer pn,String materCode,String matername,HttpSession session){
        System.out.println("需求计划查询初次进入");
        PageInfo<Orders> ordersPageInfo = planOrderPage(pn,materCode,matername, session);
        StringBuilder  str= new StringBuilder ("<TABLE cellSpacing=1 cellPadding=3 width=\"100%\" border=0 id=\"table1\" style=\"text-align: center\">   <TBODY> <TR class=t1>       <TD noWrap align=middle>选择</TD>        <TD noWrap align=middle>序号</TD>        <TD noWrap align=middle>物资编号</TD>        <TD noWrap align=middle>物资名称</TD>        <TD noWrap align=middle>数量</TD>       <TD noWrap align=middle>采购类型</TD>       <TD noWrap align=middle>采购进度</TD>    </TR>   ");

        //判断查询到的信息不为空
        if (ordersPageInfo!=null){
            //获取当前页面的信息数量
            int InfoSize = ordersPageInfo.getSize();
            for (int i=0;i<InfoSize;i++){
                //字符串拼接
                str.append("<TR class=t2> <td align=center>  <input type=\"checkbox\" name=\"checkbox\" id="+ordersPageInfo.getPageNum()+" value="+ordersPageInfo.getList().get(i).getId()+" onclick=\"opp(true)\" > </td> <td>"
                        +ordersPageInfo.getList().get(i).getId()+"</td> <td>"+ordersPageInfo.getList().get(i).getMaterialCode()+"</td> <td>"
                        +ordersPageInfo.getList().get(i).getMaterialName()+"</td> <td>"+ordersPageInfo.getList().get(i).getAmount()+"</td> <td> 制造中心采购 </td> <td> 未确认 </td> ");
            }
        }
        str.append("</TBODY> </TABLE>");
        return str.toString();
    }


    /**
     * 分页查询的调用分页方法
     * @param pn    查询第几页
     * @param session session对象
     */
    public PageInfo<Orders>  planOrderPage(Integer pn,String materCode,String matername, HttpSession session){
        PageHelper.startPage(pn,5);

        //接收查询信息集合
        List<Orders> orders=null;
        //获取所有查询的物资信息
        //判断是否未输入信息
        if (materCode==""&&matername==""){
            orders = planOrdersService.FinAllOrder();
        } else{
           orders = planOrdersService.FinAllOrderCondition(materCode,matername);
        }
        //判断查询到的信息是否为空，
        if (orders!=null){
            //通过PageInfo类解析分页结果,admins的是我们获取数据的数组
            PageInfo<Orders> info = new PageInfo<>(orders);
            session.setAttribute("pageInfo",info);
            //info.getList()可以查看当前info的所有信息
            System.out.println(info.getList());
            return info;
        }
        return null;

    }


    //根据id获取信息
    @GetMapping("/planOrderidSelect")
    @ResponseBody
    public boolean planOrderid(Integer id,HttpSession session){
        System.out.println("根据id查询信息");
        Orders orders = planOrdersService.FinbyOrder(id);
        if (orders!=null){
            session.setAttribute("planOrderid",orders);
            return true;
        }
      return false;
    }

    //根据id删除信息
    @GetMapping("/planReamOrder")
    @ResponseBody
    public boolean reamOrder(String str){
        System.out.println("根据id删除信息");
        if (!"".equals(str)){
            //将str的最后一个','截取掉
            str = str.substring(0, str.length() - 1);
            //将截取到的id字符转换为数组
            String[] split = str.split(",");
            try {
                boolean fal = planOrdersService.CircularDeletion(split);
            }catch (Exception e){
                System.out.println("循环删除异常");
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    //保存
    @GetMapping("/planupdate")
    @ResponseBody
    public boolean updateor(String materid,String materialCode,String materialName,String materamount,String measureUnit,String materunitPrice,
                            String materendDate,String materemail,String materphone,String materfax,String materremark){
        System.out.println("进入物资信息修改的控制层");
        System.out.println("id="+materid+"产品编码="+materialCode+"产品名称="+materialName+"产品数量="+materamount+"计量单位="+measureUnit+"单价="
                +materunitPrice+"交货期="+materendDate+"邮箱="+materemail+"联系电话="+materphone+"联系传真="+materfax+"备注="+materremark);
        // 时间格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        Orders order=new Orders();
       order.setId(Long.parseLong(materid));//设置id
       order.setMaterialCode(materialCode);//设置产品编号
       order.setMaterialName(materialName);//设置产品姓名
       order.setAmount(materamount);//数量
       order.setMeasureUnit(measureUnit);//计量单位
       order.setEmail(materemail);//邮箱
       order.setPhone(materphone);//联系电话
       order.setFax(materfax);//联系传真
       order.setRemark(materremark);//备注
        //单价
        BigDecimal bd=new BigDecimal(materunitPrice);
        bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setUnitPrice(bd);
        try {
            //开始交货日期
            order.setEndDate(sdf.parse(materendDate));
        } catch (ParseException e) {
            System.out.println("日期类型转换异常");
            e.printStackTrace();
        }
        System.out.println("order参数设置完毕"+order);
        boolean fal = planOrdersService.UpdateOrder(order);
        if (fal){
            System.out.println("修改成功");
            return true;
        }
        return false;
    }


}
