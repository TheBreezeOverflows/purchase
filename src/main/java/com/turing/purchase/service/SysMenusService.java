package com.turing.purchase.service;


import com.turing.purchase.entity.SysMenus;

import java.util.List;

public interface SysMenusService {

    /**
     * 获取所有菜单
     * @return 所有菜单集合
     */
    List<SysMenus> getAllMenus();

    /**
     * 获取该角色下的菜单
     * @param userName
     * @return
     */
    List<SysMenus> getRoleMenus(String userName);

}
