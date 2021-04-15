package com.turing.purchase.controller;

import com.turing.purchase.entity.SysMenus;
import com.turing.purchase.service.SysMenusService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class SysMenusController {

    @Autowired
    private SysMenusService sysMenusService;


    @PostMapping("/")
    @ResponseBody
    public List<SysMenus> getMenu(){
        Subject subject = SecurityUtils.getSubject();
        String userName = (String)subject.getPrincipal();

        return sysMenusService.getRoleMenus(userName);
    }

}
