package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Menu;
import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.mapper.MenuMapper;
import com.capgemini.cn.deemo.mapper.MenuRoleMapper;
import com.capgemini.cn.deemo.service.MenuService;
import com.capgemini.cn.deemo.utils.UserUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hasaker
 * @since 2019/9/8 11:04
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final MenuRoleMapper menuRoleMapper;

    public MenuServiceImpl(MenuMapper menuMapper, MenuRoleMapper menuRoleMapper) {
        this.menuMapper = menuMapper;
        this.menuRoleMapper = menuRoleMapper;
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
    public Integer addMenu(Menu menu) {
        return menuMapper.addMenu(menu);
    }

    @Override
    public Map<String, List<Menu>> getMenuTree() {
        Map<String, List<Menu>> menuTree = new HashMap<>();
        List<Menu> allMenus = menuMapper.getAllMenus();
        List<Role> userRoles = UserUtils.getCurrentUser().getRoles();

        for (Menu menu : allMenus) {
            if (menu.getParentMenuId().equals(0L) && containRoles(userRoles,
                    menuRoleMapper.getRolesByMenuId(menu.getMenuId()))) {
                List<Menu> children = new ArrayList<>();
                for (Menu child : allMenus) {
                    if (child.getParentMenuId().equals(menu.getMenuId()) && containRoles(userRoles,
                            menuRoleMapper.getRolesByMenuId(menu.getMenuId()))) {
                        children.add(child);
                    }
                }
                menuTree.put(menu.getMenuName(), children);
            }
        }

        return menuTree;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    private boolean containRoles(List<Role> userRoles, List<Role> menuRoles) {
        List<Long> userRoleIds = new ArrayList<>();
        List<Long> menuRoleIds = new ArrayList<>();
        for (Role role : userRoles) {
            userRoleIds.add(role.getRoleId());
        }
        for (Role role : menuRoles) {
            menuRoleIds.add(role.getRoleId());
        }

        for (Long menuRoleId : menuRoleIds) {
            if (userRoleIds.contains(menuRoleId)) {
                return true;
            }
        }

        return false;
    }
}
