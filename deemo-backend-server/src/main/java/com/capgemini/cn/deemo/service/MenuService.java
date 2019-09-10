package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author hasaker
 * @since 2019/9/8 11:02
 */
public interface MenuService {

    Menu getMenuByMenuId(Long menuId);

    Menu getMenuByMenuName(String menuName);

    Integer addMenu(Menu menu);

    Map<String, List<Menu>> getMenuTree();

    List<Menu> getAllMenus();
}
