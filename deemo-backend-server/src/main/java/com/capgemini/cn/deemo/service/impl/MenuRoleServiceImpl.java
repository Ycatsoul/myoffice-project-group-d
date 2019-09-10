package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.mapper.MenuRoleMapper;
import com.capgemini.cn.deemo.service.MenuRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:05
 */
@Service
public class MenuRoleServiceImpl implements MenuRoleService {

    private final MenuRoleMapper menuRoleMapper;

    public MenuRoleServiceImpl(MenuRoleMapper menuRoleMapper) {
        this.menuRoleMapper = menuRoleMapper;
    }

    @Override
    public List<Role> getRolesByMenuId(Long menuId) {
        return menuRoleMapper.getRolesByMenuId(menuId);
    }

    @Override
    public Integer addMenuRole(Long menuRoleId, Long menuId, Long roleId) {
        return menuRoleMapper.addMenuRole(menuRoleId, menuId, roleId);
    }
}
