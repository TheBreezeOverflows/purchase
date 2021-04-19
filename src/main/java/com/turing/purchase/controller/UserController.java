package com.turing.purchase.controller;

import com.turing.purchase.entity.Employee;
import com.turing.purchase.entity.SysUsers;
import com.turing.purchase.service.EmployeeService;
import com.turing.purchase.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 登录控制层
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService UserService;
    @Autowired
    private EmployeeService employeeService;

    //注册
    @PostMapping("/userRegist")
    public String userRegist(@RequestParam("uName") String userName,@RequestParam("uPassword")  String passWord){
        System.out.println("用户名"+userName);
        //根据用户名获取用户对象
        SysUsers byuser = UserService.findByName(userName);
        //判断用户名是否被注册
        if (ObjectUtils.isEmpty(byuser)){
            try {
                SysUsers user=new SysUsers();
                //设置用户名
                user.setLoginId(userName);
                //设置密码
                user.setPassword(passWord);
                UserService.regist(user);
                return "redirect:/login";
            }catch (Exception e){
                System.out.println("注册失败");
                return "redirect:/regist";
            }
        }
        System.out.println("用户名被注册");
        return "redirect:/regist";
    }

    //注销
    @GetMapping("/userLogout")
    public String userLogout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    //登录
    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("uName") String userName, @RequestParam("uPassword")  String passWord){
        System.out.println("进入登录控制器");
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        try {
            //获取令牌
            subject.login(new UsernamePasswordToken(userName, passWord));
            //登录成功
                //获取用户信息
            Employee emp = employeeService.getEmpByUserName((String) subject.getPrincipal());
                //存入session
            subject.getSession().setAttribute("emp",emp);
            //跳转主页
            return "redirect:/myindex";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:/login";
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public String updatePwd(@RequestParam("rad") String loginId,@RequestParam("but") String password,HttpSession session){
        int result = UserService.updatePwd(loginId, password);
        if(result > 0){
            //修改密码成功
            session.setAttribute("resultStr","修改成功");
        }else{
            //修改密码失败
            session.setAttribute("resultStr","修改失败");
        }
        session.setAttribute("resultUrl","/supplymanPwdUpdate");
        return "/updateResult";
    }
}
