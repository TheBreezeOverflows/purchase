package com.turing.purchase.config;

import com.turing.purchase.realm.UserRealm;
import org.springframework.context.annotation.Bean;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {


    //1、配置Shiro的过滤器---让Shiro拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        //创建Shiro过滤器工厂对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //创建一个Map用来保存拦截器
       Map<String,String> map = new HashMap<>();
        //配置公共资源（anon）---不用认证即可访问
        map.put("/*.js", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/bootstrap/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/contractmanager/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/css/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/easyui/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/imgs/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/js/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/mycss/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/planman/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/queryandqueto/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/**/supplyman/**", "anon");//anon表示不需要认证和授权就能访问
        map.put("/login", "anon");//登录
        map.put("/regist", "anon");//注册
        //配置受限资源（authc）---需要认证和授权才能访问
        map.put("/**", "authc");//把拦截所有的过滤器放到最后

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置拦截后跳转的页面（默认是login.jsp，也可以修改）
        shiroFilterFactoryBean.setLoginUrl("/login");
            return shiroFilterFactoryBean;
    }

    //设置安全管理器
    @Bean
    public DefaultWebSecurityManager dafalutsecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager desecuitymanager=new DefaultWebSecurityManager();
        //添加reaml
        desecuitymanager.setRealm(userRealm);

        return desecuitymanager;
    }

    //设置reaml
    @Bean
    public UserRealm userRealm(){
        UserRealm ureaml=new UserRealm();
        //创建加密匹配器
       /* HashedCredentialsMatcher matcher =new HashedCredentialsMatcher("md5");
        //添加散列次数
        matcher.setHashIterations(128);
        //添加加密匹配器
        ureaml.setCredentialsMatcher(matcher);*/
        return ureaml;
    }



}
