package com.turing.purchase.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
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

        registry.addViewController("/goodsSelect").setViewName("supplyman/goodsSelect");
        registry.addViewController("/product_save").setViewName("supplyman/product_save");
        registry.addViewController("/product_update").setViewName("supplyman/product_update");

        registry.addViewController("/category").setViewName("supplyman/category");
        registry.addViewController("/category_save").setViewName("supplyman/category_save");
        registry.addViewController("/category_update").setViewName("supplyman/category_update");

        registry.addViewController("/Project_list").setViewName("supplyman/Project_list");
        registry.addViewController("/修改公开求购报价书").setViewName("supplyman/修改公开求购报价书");

        registry.addViewController("/Order_wbxjfa_list").setViewName("supplyman/Order_wbxjfa_list");
        registry.addViewController("/报价历史记录").setViewName("supplyman/报价历史记录");

        registry.addViewController("/ProviderConsignment").setViewName("supplyman/ProviderConsignment");
        registry.addViewController("/contract_view").setViewName("supplyman/contract_view");



    }
}
