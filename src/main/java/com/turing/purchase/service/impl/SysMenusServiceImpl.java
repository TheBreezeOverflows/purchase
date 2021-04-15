package com.turing.purchase.service.impl;

import com.turing.purchase.entity.Attributes;
import com.turing.purchase.entity.SysMenus;
import com.turing.purchase.mapper.SysMenusMapper;
import com.turing.purchase.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenusServiceImpl implements SysMenusService {

    @Autowired(required = false)
    private SysMenusMapper sysMenusMapper;

    @Override
    public List<SysMenus> getAllMenus() {
        List<SysMenus> menus = sysMenusMapper.selectByExample(null);
//        System.out.println(menus);
//        return menus;
        return findChildrenWhile(0,menus);
    }

    @Override
    public List<SysMenus> getRoleMenus(String userName) {
        List<SysMenus> menus = sysMenusMapper.getRoleMenus(userName);
        return findChildrenWhile(0,menus);
    }

    /**
     * 递归寻找子菜单
     * @param id 父菜单id
     * @param allMenu 在所有菜单中寻找
     * @return 该父菜单下的子菜单集合
     */
    private List<SysMenus> findChildrenWhile(int id, List<SysMenus> allMenu) {
        List<SysMenus> childMenu = new ArrayList<>();
        for (SysMenus menu: allMenu) {
            if(menu.getParentId().intValue() == id){
                //开始递归
                menu.setChildren(findChildrenWhile(menu.getId().intValue(),allMenu));
                //获取属性对象
                Attributes attrs = new Attributes();
                attrs.setUrl(menu.getLinkUrl());
                //设置属性对象
                menu.setAttributes(attrs);
                //增加子菜单
                childMenu.add(menu);
            }
        }
        return childMenu;
    }
}
