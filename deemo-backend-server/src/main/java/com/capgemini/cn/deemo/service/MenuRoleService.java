package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Role;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 11:02
 */
public interface MenuRoleService {

    List<Role> getRolesByMenuId(Long menuId);
}
