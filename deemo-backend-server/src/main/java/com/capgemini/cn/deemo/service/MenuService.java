package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Menu;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:02
 */
public interface MenuService {

    Menu getMenuByMenuId(Long menuId);

    Menu getMenuByMenuName(String menuName);

    List<Menu> getAllMenus();
}
