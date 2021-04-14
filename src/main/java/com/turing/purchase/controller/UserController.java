package com.turing.springshiro2.controller;

import com.turing.springshiro2.config.entity.User;
import com.turing.springshiro2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 长沙图灵科技学院
 * @Company www.tulingedu.com
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/userRegist")
    public String userRegist(User user){
        try {
            userService.regist(user);
            return "redirect:/login";
        }catch (Exception e){
            System.out.println("注册失败");
            return "redirect:/regist";
        }
    }

    //注销
    @GetMapping("/userLogout")
    public String userLogout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    //登录
    @PostMapping("/userLogin")
    public String userLogin(String userName,String passWord){

        //获取主体
        Subject subject = SecurityUtils.getSubject();
        try {
            //获取令牌
            subject.login(new UsernamePasswordToken(userName, passWord));
            //登录成功
            return "redirect:/index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:/login";
    }
}
