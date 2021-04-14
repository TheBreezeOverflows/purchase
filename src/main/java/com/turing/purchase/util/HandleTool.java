package com.turing.purchase.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类，简化获取数据
 */
public class HandleTool {

    public static Map<String,String> map;

    static{
        map = new HashMap<>();
        //公司性质
        map.put("G000-1","国有企业");
        map.put("G000-2","集体企业");
        map.put("G000-3","股份合作企业");
        map.put("G000-4","有限责任公司");
        map.put("G000-5","股份有限公司");
        map.put("G000-6","私营独资企业");
        map.put("G000-7","中外合资经营企业");
        //采购状态代码
        map.put("C001-10","需求计划未确认");
        map.put("C001-20","未编采购计划");
        map.put("C001-30","采购计划未报批");
        map.put("C001-40","采购计划未审批");
        map.put("C001-50","采购计划未下达");
        map.put("C001-51","采购计划审批不通过");
        map.put("C001-60","未编询价书");
        map.put("C001-70","询价书未发出");
        map.put("C001-80","询价书已发出");
        map.put("C001-90","询价已截止");
        map.put("C001-100","已揭示报价");
        map.put("C001-110","合同申请未报批");
        map.put("C001-120","合同申请未审核");
        map.put("C001-130","合同申请计划员已审核");
        map.put("C001-131","合同申请计划员审核不通过");
        map.put("C001-140","合同申请部长审批已审批");
        map.put("C001-141","合同申请部长审批不通过");
        map.put("C001-150","合同已签订");
        map.put("C001-160","合同已归档");
        //运输方式
        map.put("Y001-1","铁快");
        map.put("Y001-2","EMS航空");
        map.put("Y001-3","中铁快运");
        map.put("Y001-4","普邮");
        map.put("Y001-5","送货");
        map.put("Y001-6","提货");
        map.put("Y001-7","汽运");
        //包装要求
        map.put("B001-1","散装");
        map.put("B001-2","托盘");
        map.put("B001-3","卷带");
        map.put("B001-4","管装");
        map.put("B001-5","防静电");
        map.put("B001-6","防震,防潮,防挤压");
        map.put("B001-7","防摩擦");
        map.put("B001-8","整箱");
        map.put("B001-9","整卷");
        //物资类别
        map.put("W000-W40","金属模电阻");
        map.put("W000-W41","线绕电阻");
        map.put("W000-W60","二级管");
        map.put("W000-W61","发光器件");
        map.put("W000-W62","三级管");
        map.put("W000-W64","晶振");
        //是否可用
        map.put("S004-0","不可用");
        map.put("S004-1","可用");
        //付款方式
        map.put("F001-1","汇兑（电汇、信汇）");
        map.put("F001-2","托收承付");
        map.put("F001-3","银行支票");
        map.put("F001-4","银行本票");
        map.put("F001-5","银行汇票");
        map.put("F001-6","委托收款");
        map.put("F001-7","商业票据");
        //运输费用负担
        map.put("Y002-1","供方");
        map.put("Y002-2","需方");
        //交(提)货地点
        map.put("J000-1","供方所在地");
        map.put("J000-2","需方工厂");
        //质量要求及技术标准
        map.put("Z000-1","军标");
        map.put("Z000-2","国际标");
        map.put("Z000-3","铁标");
        map.put("Z000-4","国标");
        map.put("Z000-5","需方提供的技术文件标准");
        map.put("Z000-6","供方企业标准");
        map.put("Z000-7","其他");
        //付款条件
        map.put("F000-1","货到验收合格、自收到有效发票后90天付款");
        map.put("F000-2","货到验收合格、自收到有效发票后付款");
        map.put("F000-3","现场考核后付款");
        map.put("F000-4","货到验收合格、自收到有效发票后30天付款");
        map.put("F000-5","款到发货");
        map.put("F000-6","其它约定");
        //验收标准
        map.put("Y000-1","国际（国家）标准");
        map.put("Y000-2","行业标准");
        map.put("Y000-3","企业标准");
        //包装物是否回收
        map.put("B000-0","包装物不回收");
        map.put("B000-1","包装物回收");
        //税率
        map.put("S000-1","0%");
        map.put("S000-2","4%");
        map.put("S000-3","6%");
        map.put("S000-4","17%");
        //生产年限
        map.put("S003-1","一年");
        map.put("S003-2","两年");
        map.put("S003-3","三年");
    }

    /**
     * 获取状态
     * @param status
     * @return
     */
    public static String getStatusStr(String status){
        String str = "";
        switch (status){
            case "B001-1":
                str = "结果未发";
                break;
            case "B001-2":
                str = "中标";
                break;
            case "B001-3":
                str = "未中";
                break;
            default:
                break;
        }
        return str;
    }

    /**
     * 获取编号所对应的名称 (不包含状态)
     * @param no
     * @return
     */
    public static String getAllNoStr(String no){
        String str = map.get(no);
        return str;
    }

    /**
     * 通过名称获取编号 (不包含状态)
     * @param name 名称
     * @return
     */
    public static String getStrOfAllNo(String name){
        for (Map.Entry<String,String> entry : map.entrySet()){
            if(entry.getValue().equals(name)){
                return entry.getKey();
            }
        }
        return "";
    }

}
