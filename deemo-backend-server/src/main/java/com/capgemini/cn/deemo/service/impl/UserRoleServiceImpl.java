package com.capgemini.cn.deemo.service.impl;

import com.capgemini.cn.deemo.data.domain.Role;
import com.capgemini.cn.deemo.mapper.UserRoleMapper;
import com.capgemini.cn.deemo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 00:54
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return userRoleMapper.getRolesByUserId(userId);
    }
}
