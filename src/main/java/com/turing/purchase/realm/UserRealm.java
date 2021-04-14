package com.turing.purchase.realm;


import moabi.lsp.lsplsp.entity.user;
import moabi.lsp.lsplsp.service.UserService;
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

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


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
        System.out.println("用户名"+Token.getPrincipal());
        String uname=(String) Token.getPrincipal();
        user byuser = userService.findByName(uname);
        System.out.println(byuser.getName()+"密码："+byuser.getPwd()+"盐值："+byuser.getSalt());

        if (!ObjectUtils.isEmpty(byuser)){
            return new SimpleAuthenticationInfo(byuser.getName(),byuser.getPwd(), ByteSource.Util.bytes(byuser.getSalt()),this.getName());
        }
        return null;

    }
}
