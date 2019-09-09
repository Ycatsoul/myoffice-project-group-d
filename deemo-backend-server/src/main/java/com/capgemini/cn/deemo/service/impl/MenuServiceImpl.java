package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Menu;
import com.capgemini.cn.deemo.mapper.MenuMapper;
import com.capgemini.cn.deemo.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:04
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public Menu getMenuByMenuId(Long menuId) {
        return menuMapper.getMenuByMenuId(menuId);
    }

    @Override
    public Menu getMenuByMenuName(String menuName) {
        return menuMapper.getMenuByMenuName(menuName);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
