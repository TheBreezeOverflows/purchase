package com.turing.purchase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = "com.turing.purchase.mapper")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/leftRequire").setViewName("leftRequire");
        registry.addViewController("/bar").setViewName("bar");
        registry.addViewController("/mainRequire").setViewName("mainRequire");
        registry.addViewController("/login").setViewName("login");

        //供应商平台
        registry.addViewController("/supplymanLook").setViewName("supplyman/supplymanLook");
        registry.addViewController("/supplymanPwdUpdate").setViewName("supplyman/supplymanPwdUpdate");

        /**
         * 计划员
         */
        //业务处理
        registry.addViewController("planman/bianzhicaigoujihua").setViewName("planman/bianzhicaigoujihua");
        registry.addViewController("planman/category").setViewName("planman/category");
        registry.addViewController("planman/ddtz_ddmx").setViewName("planman/ddtz_ddmx");
        registry.addViewController("planman/jiffprov_look").setViewName("planman/jiffprov_look");
        registry.addViewController("planman/leftRequire").setViewName("planman/leftRequire");
        registry.addViewController("planman/mainRequire").setViewName("planman/mainRequire");
        registry.addViewController("planman/Order_newform").setViewName("planman/Order_newform");
        registry.addViewController("planman/Order_wbxjfa_list").setViewName("planman/Order_wbxjfa_list");
        registry.addViewController("planman/Order_ytb_list").setViewName("planman/Order_ytb_list");
        registry.addViewController("planman/pclass_select").setViewName("planman/pclass_select");
        registry.addViewController("planman/print_order_detail").setViewName("planman/print_order_detail");
        registry.addViewController("planman/Project_list").setViewName("planman/Project_list");
        registry.addViewController("planman/Project_list3").setViewName("planman/Project_list3");
        registry.addViewController("planman/Project_list4").setViewName("planman/Project_list4");
        registry.addViewController("planman/Project_list4_list").setViewName("planman/Project_list4_list");
        registry.addViewController("planman/provider_cx").setViewName("planman/provider_cx");
        registry.addViewController("planman/provider_cx.jbxxlist").setViewName("planman/provider_cx.jbxxlist");
        registry.addViewController("planman/provider_cx_jbxxlist").setViewName("planman/provider_cx_jbxxlist");
        registry.addViewController("planman/supply").setViewName("planman/supply");
        registry.addViewController("planman/supply2").setViewName("planman/supply2");
        registry.addViewController("planman/update_xuqiujihua").setViewName("planman/update_xuqiujihua");
        registry.addViewController("planman/updatecategory").setViewName("planman/updatecategory");
        registry.addViewController("planman/wangyeduihakuang").setViewName("planman/wangyeduihakuang");
        registry.addViewController("planman/work_log").setViewName("planman/work_log");
        registry.addViewController("planman/work_log01").setViewName("planman/work_log01");
        registry.addViewController("planman/work_log02").setViewName("planman/work_log02");
        registry.addViewController("planman/work_log02").setViewName("planman/work_log02");
        registry.addViewController("planman/xjfatz_xjfamx").setViewName("planman/xjfatz_xjfamx");
        registry.addViewController("planman/xjfatz_xjfamx2").setViewName("planman/xjfatz_xjfamx2");
        registry.addViewController("planman/xjfatz_xjfamx3").setViewName("planman/xjfatz_xjfamx3");
    }
}
