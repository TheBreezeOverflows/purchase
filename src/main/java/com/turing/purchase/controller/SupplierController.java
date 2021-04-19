package com.turing.purchase.controller;

import com.turing.purchase.entity.Supplier;
import com.turing.purchase.service.SupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/supplyman")
//@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getSupplier")
    public String getSupplier(HttpSession session){
        Supplier supplier = supplierService.getSupplierInfo((String) SecurityUtils.getSubject().getPrincipal());
        session.setAttribute("supplier",supplier);
        return "redirect:/supplymanLook";
    }

    @GetMapping("/getLoginId")
    @ResponseBody
    public String getLoginId(){
        String loginId = (String)SecurityUtils.getSubject().getPrincipal();
        return loginId;
    }

}
