package com.turing.purchase.config;

import com.turing.purchase.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig2 {
    //1.Shiro过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        //创建一个Shiro过滤器，用来拦截所有请求
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String,String> map = new HashMap<>();

        //设置公共资源
        map.put("/login", "anon");//anon表示不需要认证和授权就能访问
        map.put("/regist", "anon");//anon表示不需要认证和授权就能访问
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

        //设置受限资源
        map.put("/**", "authc");//authc表示需要认证和授权才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //修改默认拦截后要跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");//这里跳转login.html

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }

    //2.安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        //注意要创建带web的安全管理器
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //指定Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //3.自定义Realm
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
