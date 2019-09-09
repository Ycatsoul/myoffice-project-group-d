package com.capgemini.cn.deemo.service;

import com.capgemini.cn.deemo.data.domain.Role;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:53
 */
public interface UserRoleService {

    List<Role> getRolesByUserId(Long userId);
}
