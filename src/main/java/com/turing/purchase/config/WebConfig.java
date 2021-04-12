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

    }
}
