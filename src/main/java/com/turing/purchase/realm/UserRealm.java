package com.turing.purchase.realm;


import com.turing.purchase.entity.SysMenus;
import com.turing.purchase.entity.SysUsers;
import com.turing.purchase.service.SysMenusService;
import com.turing.purchase.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService UserService;

    @Autowired
    private SysMenusService sysMenusService;


    /**
     * 授权
     * @param principalCollection 当前用户对象
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 验证
     * @param Token 前端验证对象
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        System.out.println("登录验证");
        //获取用户名
        //System.out.println("用户名"+Token.getPrincipal());
        String uname=(String) Token.getPrincipal();
        SysUsers byuser = UserService.findByName(uname);
        //判断是否有该用户
        if (!ObjectUtils.isEmpty(byuser)){
            //System.out.println("用户名："+byuser.getLoginId()+"密码："+byuser.getPassword()+"状态："+byuser.getStatus());
            return new SimpleAuthenticationInfo(byuser.getLoginId(),byuser.getPassword(), this.getName());
        }
        return null;

    }
}
